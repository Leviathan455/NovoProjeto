package br.csi.Animo;

import br.csi.Animo.service.SwaggerService;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnimoApplication {

	private final SwaggerService swaggerService;

	public AnimoApplication(SwaggerService swaggerService) {
		this.swaggerService = swaggerService;
	}

	public static void main(String[] args) {
		SpringApplication.run(AnimoApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenAPI() {
		return swaggerService.createOpenAPI();
	}
}
