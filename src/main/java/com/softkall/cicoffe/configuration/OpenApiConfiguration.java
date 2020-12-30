package com.softkall.cicoffe.configuration;


import com.softkall.cicoffe.configuration.properties.WebConfigurationProperties;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;


/**
 * @author Nidhal Dogga
 * @created 11/14/2020 12:04 AM
 * SoftKall™ All rights reserved.
 */


@Configuration
public class OpenApiConfiguration {

  public static final String securitySchemeName = "JWT Token";

  @Bean
  public OpenAPI openApi(WebConfigurationProperties webConfiguration) {
    return new OpenAPI()
            .servers(Collections.singletonList(new Server().url(webConfiguration.getServerUrl())))
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
