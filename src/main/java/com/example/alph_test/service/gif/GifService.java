package com.example.alph_test.service.gif;

import com.example.alph_test.dto.GifResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class GifService {

    @Value("${gif-service.greater-search-key}")
    private String greaterKey;

    @Value("${gif-service.not-greater-search-key}")
    private String notGreaterKey;

    @Value("${gif-service.response-random-range}")
    private Integer randomRange;

    private final GifServiceClient gifServiceClient;

    public String getRandomGifUrl(int compareResult) {
        GifSearchParams params = new GifSearchParams(
                compareResult > 0 ? greaterKey : notGreaterKey,
                1,
                ThreadLocalRandom.current().nextInt(randomRange));
        return gifServiceClient.searchGifs(params).getData()[0].getUrl();
    }
}
