package com.example.alph_test;

import com.example.alph_test.service.currency.CurrencyServiceClient;
import com.example.alph_test.service.gif.GifServiceClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AlphTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlphTestApplication.class, args);
    }

    @Bean
    public CurrencyServiceClient currencyServiceClient(@Value("${currency-exch-service.url}") String url,
                                                       @Value("${currency-exch-service.app-id}") String appId,
                                                       @Value("${currency-exch-service.base-currency-code}") String baseCode) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(template -> template
                        .query("app_id", appId)
                        .query("base", baseCode))
                .target(CurrencyServiceClient.class, url);
    }

    @Bean
    public GifServiceClient gifServiceClient(@Value("${gif-service.url}") String url,
                                             @Value("${gif-service.api-key}") String apiKey) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .requestInterceptor(template -> template
                        .query("api_key", apiKey))
                .target(GifServiceClient.class, url);
    }
}
