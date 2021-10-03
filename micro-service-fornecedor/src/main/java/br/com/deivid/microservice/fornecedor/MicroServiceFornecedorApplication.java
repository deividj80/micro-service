package br.com.deivid.microservice.fornecedor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class MicroServiceFornecedorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroServiceFornecedorApplication.class, args);
    }

}
