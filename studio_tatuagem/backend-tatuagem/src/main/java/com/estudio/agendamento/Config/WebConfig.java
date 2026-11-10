//configurações importantes para a comunicação entre o front-end e o back-end
// para que o front-end acesse a API do back-end sem problemas de CORS (Cross-Origin Resource Sharing).
package com.estudio.agendamento.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//vai permitir que o front-end acesse a API do back-end
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}