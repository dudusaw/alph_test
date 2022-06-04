package com.example.alph_test.service.currency;

import com.example.alph_test.dto.HistoryRates;
import feign.Param;
import feign.RequestLine;

import java.time.LocalDate;

public interface CurrencyServiceClient {

    @RequestLine("GET /historical/{date}.json")
    HistoryRates getRatesForDate(@Param("date") LocalDate date);
}
