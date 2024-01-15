package com.frizo.grpc.server.base;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

public class GrpcServerInterceptor implements ServerInterceptor {
  @Override
  public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
      ServerCall<ReqT, RespT> serverCall,
      Metadata metadata,
      ServerCallHandler<ReqT, RespT> serverCallHandler) {
    System.out.println("----------------------------------------------------------");
    System.out.println("[GrpcServerInterceptor] received serverCall: " + serverCall);
    System.out.println("[GrpcServerInterceptor] received metadata: " + metadata);
    System.out.println("----------------------------------------------------------");
    return serverCallHandler.startCall(serverCall, metadata);
  }
}
