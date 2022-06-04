package com.example.alph_test.service.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    @Value("${currency-exch-service.past-days}")
    private Integer pastDays;

    private final CurrencyServiceClient currencyServiceClient;

    public int compareCurrencyWithDefault(String currencyCode) {
        LocalDate today = LocalDate.now();
        LocalDate past = today.minusDays(pastDays);
        float todayRate = currencyServiceClient.getRatesForDate(today).getRates().get(currencyCode);
        float pastRate = currencyServiceClient.getRatesForDate(past).getRates().get(currencyCode);
        if (todayRate < 0 || pastRate < 0) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "currency api unexpected response");
        }
        return Float.compare(todayRate, pastRate);
    }
}
