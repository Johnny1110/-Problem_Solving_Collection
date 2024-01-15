package com.frizo.grpc.controller;

import com.frizo.grpc.service.GrpcClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GrpcClientController {

    @Autowired
    private GrpcClientService grpcClientService;

    @GetMapping("/hello")
    public String printMessage(@RequestParam(defaultValue = "Johnny") String name) {
        return grpcClientService.sendMessage(name);
    }

    @GetMapping("/exchangerate")
    public Object printMessage(String currencyCode, String exchangeCurrencyCode) {
        return grpcClientService.getExchangeRate(currencyCode, exchangeCurrencyCode);
    }

}
