package com.frizo.grpc.client.controller;

import com.frizo.grpc.api.ExchangeRate;
import com.frizo.grpc.api.ExchangeRateQueryServiceGrpc;
import com.frizo.grpc.client.base.GrpcClientGateWay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DemoController {
  @Autowired private GrpcClientGateWay grpcClientGateWay;

  @GetMapping("/queryExchangeRate/currency/{currency}/exchangeCurrency/{exchangeCurrency}")
  public Object queryExchangeRate(
      @PathVariable("currency") String currency,
      @PathVariable("exchangeCurrency") String exchangeCurrency) {
    ExchangeRateQueryServiceGrpc.ExchangeRateQueryServiceBlockingStub stub =
        grpcClientGateWay.getExchangeRateQueryServiceBlockingStub();

    ExchangeRate.ExchangeRateReq exchangeRateReq =
        ExchangeRate.ExchangeRateReq.newBuilder()
            .setCurrencyCode("USD")
            .setExchangeCurrencyCode("TWD")
            .build();
    ExchangeRate.ExchangeRateResp resp = stub.getExchangeRate(exchangeRateReq);
    return Map.of("price", resp.getPrice(), "high", resp.getPriceHigh24H(), "low", resp.getPriceLow24H());
  }
}
