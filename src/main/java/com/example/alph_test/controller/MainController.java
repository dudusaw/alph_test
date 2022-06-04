package com.example.alph_test.controller;

import com.example.alph_test.dto.GifResponse;
import com.example.alph_test.service.currency.CurrencyService;
import com.example.alph_test.service.gif.GifService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final CurrencyService currencyService;
    private final GifService gifService;

    @GetMapping("/currency/compare")
    public String compareCurrency(@RequestParam(name = "code") String currencyCode) {
        int compareResult = currencyService.compareCurrencyWithDefault(currencyCode);
        String gifUrl = gifService.getRandomGifUrl(compareResult);
        return "redirect:" + gifUrl;
    }
}
