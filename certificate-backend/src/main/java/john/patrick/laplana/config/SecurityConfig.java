package john.patrick.laplana.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import john.patrick.laplana.entities.PlatformAdmin;
import john.patrick.laplana.enus.Roles;
import john.patrick.laplana.repositories.PlatformAdminRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
   
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        
        return http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors
                .configurationSource(
                    corsConfigurationSource()
                )
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .formLogin(form -> form.disable())
            .httpBasic(basic -> basic.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/auth/**", "/api/public/**").permitAll()
                .requestMatchers("/api/platform-admin/**").hasAuthority(Roles.PLATFORM_ADMIN.toString())
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .build();

    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        
        config.setAllowedOrigins(List.of("http://localhost:5173", "https://booking-system-real-one.vercel.app")); 
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("Content-Type", "X-XSRF-TOKEN", "Authorization"));
        config.setAllowCredentials(true); 
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        return source;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Value("${platform.admin.password}")
    String platformAdminPassword;

    @Value("${platform.admin.email}")
    String platformAdminemail;

    @Bean
    CommandLineRunner initAdmin(PlatformAdminRepository platformAdminRepo, PasswordEncoder encoder) {
        return args -> {
            if(!platformAdminRepo.existsByEmail("johnpatricklaplana@gmail.com")) {
                PlatformAdmin platformAdmin = new PlatformAdmin();
                platformAdmin.setEmail(platformAdminemail);
                platformAdmin.setPassword(encoder.encode(platformAdminPassword));
                platformAdmin.setFullname("you're answer to my prayers");
                platformAdmin.setRole(Roles.PLATFORM_ADMIN.toString());

                platformAdminRepo.save(platformAdmin);
            }
        };
    }

}
