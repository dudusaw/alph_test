package com.example.alph_test.dto;

import lombok.Data;

import java.util.Map;

@Data
public class HistoryRates {
    private Map<String, Float> rates;
}
