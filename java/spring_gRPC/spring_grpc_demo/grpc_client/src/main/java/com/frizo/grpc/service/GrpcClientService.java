package com.frizo.grpc.service;

import com.gash.application.grpc.exchangerate.ExchangeRateQueryServiceGrpc;
import com.gash.application.grpc.exchangerate.ExchangeRateReq;
import com.gash.application.grpc.exchangerate.ExchangeRateResp;
import com.frizo.grpc.lib.HelloReply;
import com.frizo.grpc.lib.HelloRequest;
import com.frizo.grpc.lib.MyServiceGrpc.MyServiceBlockingStub;
import com.gash.application.grpc.exchangerate.ExchangeRateQueryServiceGrpc.ExchangeRateQueryServiceStub;
;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GrpcClientService {

    // import net.devh.boot.grpc.examples.lib.SimpleGrpc.SimpleBlockingStub;
    @GrpcClient("local-grpc-server")
    private MyServiceBlockingStub myServiceStub;

    @GrpcClient("exchangerate-server")
    private ExchangeRateQueryServiceGrpc.ExchangeRateQueryServiceBlockingStub exchangeRateQueryServiceBlockingStub;

    public String sendMessage(final String name) {
        try {
            final HelloReply response = this.myServiceStub.sayHello(HelloRequest.newBuilder().setName(name).build());
            return response.getMessage();
        } catch (final StatusRuntimeException e) {
            return "FAILED with " + e.getStatus().getCode().name();
        }
    }

    public Map<String, Object> getExchangeRate(String currencyCode, String exchangeCurrencyCode) {
        com.gash.application.grpc.exchangerate.ExchangeRateReq req =
                ExchangeRateReq.newBuilder().setCurrencyCode(currencyCode)
                .setExchangeCurrencyCode(exchangeCurrencyCode)
                .build();
        ExchangeRateResp resp = this.exchangeRateQueryServiceBlockingStub.getExchangeRate(req);
        return Map.of("msgCode", resp.getMsgCode(),
                "currencyCode", resp.getCurrencyCode(),
                "exchangeCurrencyCode", resp.getExchangeCurrencyCode(),
                "price", resp.getPrice(),
                "priceHigh24H", resp.getPriceHigh24H(),
                "priceLow24H", resp.getPriceLow24H(),
                "unixTime", resp.getUnixTime());
    }

}
