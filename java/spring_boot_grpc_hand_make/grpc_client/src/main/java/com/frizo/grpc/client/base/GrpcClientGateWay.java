package com.frizo.grpc.client.base;

import com.frizo.grpc.api.ExchangeRateQueryServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.NettyChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class GrpcClientGateWay {

  @Value("${grpc.client.target_server.host}")
  private String host;

  @Value("${grpc.client.target_server.port}")
  private int port;

  private ManagedChannel channel;

  private ExchangeRateQueryServiceGrpc.ExchangeRateQueryServiceBlockingStub
      exchangeRateQueryServiceBlockingStub;

  void start() {
    channel = NettyChannelBuilder.forAddress(host, port)
            .usePlaintext()
            .keepAliveTime(30,TimeUnit.SECONDS) // grpc 沒有資料傳遞時，多久後開始向 server 發送 ping packet
            .keepAliveWithoutCalls(true) // grpc 沒有資料傳遞時，是否 keepAlive
            .keepAliveTimeout(10,TimeUnit.SECONDS) // ping packet 發送後多久沒有收到回應算超時
            .intercept(new GrpcClientInteceptor())
            .build(); //

    this.exchangeRateQueryServiceBlockingStub =
        ExchangeRateQueryServiceGrpc.newBlockingStub(channel);
    System.out.println("grpc client started.");
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  try {
                    shutdown();
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }
                }));
  }

  void shutdown() throws InterruptedException { // 調用shutdown方法後等待1秒關閉channel
    channel.shutdown().awaitTermination(1, TimeUnit.SECONDS);
    System.out.println("gRPC client shut down successfully.");
  }

  public ExchangeRateQueryServiceGrpc.ExchangeRateQueryServiceBlockingStub
      getExchangeRateQueryServiceBlockingStub() {
    return this.exchangeRateQueryServiceBlockingStub;
  }
}
