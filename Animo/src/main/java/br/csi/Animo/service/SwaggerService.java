package br.csi.Animo.service;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.stereotype.Service;

@Service
public class SwaggerService {

    public OpenAPI createOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Animo API")
                        .version("1.0")
                        .description("API para gerenciamento de animes, episódios, favoritos e reviews"));
    }
}
