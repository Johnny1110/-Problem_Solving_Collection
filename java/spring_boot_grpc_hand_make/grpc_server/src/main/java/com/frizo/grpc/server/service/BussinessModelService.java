package com.frizo.grpc.server.service;

import com.frizo.grpc.api.ExchangeRate;
import org.springframework.stereotype.Service;

@Service
public class BussinessModelService {
  public ExchangeRate.ExchangeRateResp queryExchangeRate(ExchangeRate.ExchangeRateReq request) {
    System.out.println("[BussinessModelService] queryExchangeRate -> " + request);
    return ExchangeRate.ExchangeRateResp.newBuilder()
        .setCurrencyCode("USD")
        .setExchangeCurrencyCode("TWD")
        .setPrice("0.32")
        .setPriceHigh24H("0.33")
        .setPriceLow24H("0.31")
        .build();
  }
}
