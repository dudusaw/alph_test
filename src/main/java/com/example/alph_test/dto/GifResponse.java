package com.example.alph_test.dto;

import lombok.Data;

@Data
public class GifResponse {

    private GifObject[] data;

    @Data
    public static class GifObject {
        private String url;
    }
}
