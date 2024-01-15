package com.frizo.grpc.client.base;

import io.grpc.*;

public class GrpcClientInteceptor implements ClientInterceptor {
  @Override
  public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(
      MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
    System.out.println("-----------------------------------");
    System.out.println("methodDescriptor: " + methodDescriptor);
    System.out.println("callOptions: " + callOptions);
    System.out.println("-----------------------------------");
    return channel.newCall(methodDescriptor, callOptions);
  }
}
