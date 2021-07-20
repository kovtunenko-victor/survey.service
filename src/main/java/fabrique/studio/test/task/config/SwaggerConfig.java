package fabrique.studio.test.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Survey service API doc")
                                .version("0.0.1")
                                .contact(
                                        new Contact()
                                                .email("kovtunenko.victor@gmail.com")
                                                .name("Kovtunenko Victor")
                                                )
                );
    }
}
