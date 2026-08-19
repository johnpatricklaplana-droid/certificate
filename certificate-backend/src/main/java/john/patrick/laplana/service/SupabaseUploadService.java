package john.patrick.laplana.service;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SupabaseUploadService {

    private final RestClient restClient = RestClient.create();

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.service.role.key}")
    private String serviceRoleKey;
    
    public String uploadFile(String bucket, MultipartFile file) throws IOException {
        String uniqueFileName = file.getOriginalFilename() + UUID.randomUUID().toString();
        String uploadUrl = supabaseUrl + "/storage/v1/object/" + bucket + "/" + uniqueFileName;

        restClient.post()
            .uri(uploadUrl)
            .header("Authorization", "Bearer " + serviceRoleKey)
            .header("Content-Type", file.getContentType()) 
            .body(file.getBytes())
            .retrieve()
            .toBodilessEntity();

        return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + uniqueFileName;
    }


}
