package com.rochiyat.boot.curd.postgre.jpa.lombok.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${openapi.dev.url}")
    private String devUrl;

    @Value("${openapi.prod.url}")
    private String prodUrl;

    @Bean
    public OpenAPI openAPI() {
        Server dev = new Server();
        dev.setUrl(devUrl);
        dev.setDescription("Ini server development");

        Server prod = new Server();
        prod.setUrl(prodUrl);
        prod.setDescription("Ini server production");

        Contact contact = new Contact();
        contact.setEmail("rochiyat@gmail.com");
        contact.setName("Rochiyat");
        contact.setUrl("https://rochiyat.my.id");

        License license = new License().name("MIT License").url("https://rochiyat.my.id/licenses/mit/");

        Info info = new Info()
                .title("API Management")
                .version("1.0")
                .contact(contact)
                .description("Ini dokumentasi posting untuk manage api")
                .termsOfService("https://rochiyat.my.id")
                .license(license);

        return new OpenAPI().info(info).servers(List.of(dev, prod));
    }

}
