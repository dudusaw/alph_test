package com.example.alph_test.service.gif;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GifSearchParams {

    private String q;
    private int limit;
    private int offset;
}
