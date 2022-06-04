package com.example.alph_test.service.gif;

import com.example.alph_test.dto.GifResponse;
import feign.QueryMap;
import feign.RequestLine;

public interface GifServiceClient {

    @RequestLine("GET /search")
    GifResponse searchGifs(@QueryMap GifSearchParams params);
}
