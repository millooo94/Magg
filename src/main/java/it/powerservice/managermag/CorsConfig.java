package it.powerservice.managermag;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Applica CORS su tutti gli endpoint
                .allowedOrigins("http://localhost:4200") // Origine del frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Metodi permessi
                .allowedHeaders("*") // Intestazioni consentite
                .allowCredentials(true); // Se usi cookie o autenticazione
    }
}
