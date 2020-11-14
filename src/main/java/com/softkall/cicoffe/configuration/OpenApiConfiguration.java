package com.softkall.cicoffe.configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author Nidhal Dogga
 * @since 11/14/2020 12:04 AM
 * SoftKall™ All rights reserved.
 */


@Configuration
public class OpenApiConfiguration {

  public static final String securitySchemeName = "JWT Token";

  @Bean
  public OpenAPI openApi() {
    return new OpenAPI()
            .components(new Components()
                    .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                            .type(SecurityScheme.Type.HTTP)
                            .in(SecurityScheme.In.HEADER)
                            .bearerFormat("JWT")
                            .scheme("bearer")
                    )
            );
  }

}
