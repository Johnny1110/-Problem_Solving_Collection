package com.frizo.grpc.client.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GrpcClientCommandLinerRunner implements CommandLineRunner {

    @Autowired
    GrpcClientGateWay grpcClientGateWay;

    @Override
    public void run(String... args) throws Exception {
        grpcClientGateWay.start();
    }
}
