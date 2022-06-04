package com.example.alph_test;

import com.example.alph_test.dto.HistoryRates;
import com.example.alph_test.service.currency.CurrencyService;
import com.example.alph_test.service.currency.CurrencyServiceClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class CurrencyServiceTest {

    @Value("${currency-exch-service.past-days}")
    private Integer pastDays;

    @MockBean
    CurrencyServiceClient currencyServiceClient;

    @Autowired
    CurrencyService currencyService;

    private void setupRates(Float todayRate, Float pastRate) {
        HistoryRates today = new HistoryRates();
        today.setRates(Map.ofEntries(
                Map.entry("RUB", todayRate)
        ));
        HistoryRates past = new HistoryRates();
        past.setRates(Map.ofEntries(
                Map.entry("RUB", pastRate)
        ));
        LocalDate todayDate = LocalDate.now();
        LocalDate pastDate = todayDate.minusDays(pastDays);
        Mockito.when(currencyServiceClient.getRatesForDate(todayDate)).thenReturn(today);
        Mockito.when(currencyServiceClient.getRatesForDate(pastDate)).thenReturn(past);
    }

    @ParameterizedTest
    @CsvSource({
            "10,8,1",
            "100,0,1",
            "1000000,23,1",
            "5,10,-1",
            "0,213,-1",
            "0,0,0",
            "100,100,0"
    })
    void compareCurrencyTest(Float todayRate, Float pastRate, int compareResult) throws Exception {
        setupRates(todayRate, pastRate);

        int amd = currencyService.compareCurrencyWithDefault("RUB");

        assertThat(amd).isEqualTo(compareResult);
    }

    @ParameterizedTest
    @CsvSource({
            "10,-8,1",
            "-10,0,0"
    })
    void compareCurrencyTest_shouldThrow(Float todayRate, Float pastRate, int compareResult) throws Exception {
        setupRates(todayRate, pastRate);

        assertThatThrownBy(() -> {
            currencyService.compareCurrencyWithDefault("RUB");
        });
    }
}