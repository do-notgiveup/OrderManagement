package vn.edu.likelion.OrderManagement.configuration;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ForwardedHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//// dòng này rất hay nha, nó có thể fix được bug CORS mặc dù đã có @CrosOrigin
//@OpenAPIDefinition(servers = {@Server(url = "/", description = "any description of Server URL")})
//public class CORSConfig /*implements WebMvcConfigurer*/ {
//
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                .allowedOrigins("*") // Cho phép tất cả các nguồn gốc. Có thể thay đổi cho phù hợp với yêu cầu bảo mật của bạn.
////                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Các phương thức HTTP được phép
////                .allowedHeaders("*") // Các tiêu đề HTTP được phép
////                .allowCredentials(true); // Có cho phép gửi cookie hay không
////    }
////
////    @Bean
////    public OpenAPI openAPI() {
////        return new OpenAPI()
////                .info(new Info().title("My REST"))
////                .externalDocs(new ExternalDocumentation());
////    }
////
////    @Bean
////    public ForwardedHeaderFilter forwardedHeaderFilter() {
////        return new ForwardedHeaderFilter();
////    }
//}

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(new String[]{"http://192.168.18.81:5173", "http://localhost:5173", "https://food-management-two.vercel.app"})
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
