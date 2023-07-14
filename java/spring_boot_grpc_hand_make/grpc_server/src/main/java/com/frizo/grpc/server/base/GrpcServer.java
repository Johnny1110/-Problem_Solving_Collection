package com.frizo.grpc.server.base;

import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class GrpcServer {

  @Value("${grpc.server.port}")
  private int port;

  private Server server;

  @Autowired private ExchangeRateQueryServiceBase exchangeRateQueryServiceBase;

  public void start() throws IOException {
    server =
        NettyServerBuilder.forPort(port)
                .permitKeepAliveTime(10, TimeUnit.SECONDS)
                .keepAliveTime(10, TimeUnit.SECONDS)
                .keepAliveTimeout(10, TimeUnit.SECONDS)
                .permitKeepAliveWithoutCalls(true)
            .addService(exchangeRateQueryServiceBase)
            .intercept(new GrpcServerInterceptor())
            .build()
            .start();

    System.out.println("GrpcServer started, listening on port: " + port);
    Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
  }

  private void stop() {
    if (server != null) { // 關閉服務端
      server.shutdown();
      System.out.println("GrpcServer stopped.");
    }
  }

  public void block() throws InterruptedException {
    if (server != null) { // 服務端啓動後直到應用關閉都處於阻塞狀態，方便接收請求
      System.out.println("GrpcServer blocking.");
      server.awaitTermination();
    }
  }
}
