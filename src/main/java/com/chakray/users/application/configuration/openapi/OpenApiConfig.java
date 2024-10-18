package com.chakray.users.application.configuration.openapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@PropertySource("classpath:i18n/openapi-messages.properties")
public class OpenApiConfig {

    @Value("${openapi.version}")
    private String appVersion;

    @Value("${openapi.serverUrl}")
    private String serverUrl;

    @Value("${openapi.environment}")
    private String environment;

    @Value("${openapi.contactMail}")
    private String contactMail;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Chakray - Users API")
                                .version(appVersion)
                                .description("This is an Users API")
                                .contact(new Contact().email(this.contactMail))
                )
                .addServersItem(
                        new Server()
                                .description(environment)
                                .url(this.serverUrl)
                )
                .components(
                        new Components()
                                .addParameters(
                                        HeaderName.API_KEY_HEADER,
                                        new Parameter()
                                                .in("header")
                                                .description("Api key for access api")
                                                .schema(new StringSchema())
                                                .name(HeaderName.API_KEY_HEADER)
                                                .required(true)
                                                .example("key-in-base64")
                                )
                );
    }

}