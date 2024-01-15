package com.frizo.grpc.server.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class GrpcCommandLineRunner implements CommandLineRunner {

    @Autowired
    private GrpcServer grpcServer;

    @Override
    public void run(String... args) throws Exception {
        grpcServer.start();
        grpcServer.block();
    }
}
