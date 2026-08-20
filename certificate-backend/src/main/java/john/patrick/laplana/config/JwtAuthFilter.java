package john.patrick.laplana.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import john.patrick.laplana.service.JwtService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String token = null;
        Cookie[] cookie = request.getCookies();
        if(cookie != null) {
            for (Cookie c : request.getCookies()) {
                if (c.getName().startsWith("jwt-token")) {
                    token = c.getValue();
                }
            }
        }

        try {
            Claims claims = jwtService.validateToken(token);

            UUID userId = UUID.fromString(claims.getSubject());
            String role = (String) claims.get("role");
            String email = (String) claims.get("email");

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                userId, 
                null, 
                List.of(new SimpleGrantedAuthority(role))
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            filterChain.doFilter(request, response);
        }

    }

}
