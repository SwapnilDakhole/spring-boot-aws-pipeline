package com.beanstalk_demo.config;

import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;

import javax.net.ssl.SSLException;

@Configuration
public class AppConfig {

    @Bean
    public WebClient.Builder webClientBuilder() {
        // Create a custom HttpClient that disables SSL validation
        HttpClient httpClient = HttpClient.create()
                .secure(sslContextSpec -> {
                    try {
                        sslContextSpec.sslContext(
                                SslContextBuilder.forClient()
                                        .trustManager(InsecureTrustManagerFactory.INSTANCE)
                                        .build()
                        );
                    } catch (SSLException e) {
                        e.printStackTrace();
                    }
                });

        // Use the custom HttpClient in WebClient
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient));
    }
}

