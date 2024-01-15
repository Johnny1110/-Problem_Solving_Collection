package com.frizo.grpc.api;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.29.0)",
    comments = "Source: ExchangeRate.proto")
public final class ExchangeRateQueryServiceGrpc {

  private ExchangeRateQueryServiceGrpc() {}

  public static final String SERVICE_NAME = "com.gash.grpc.lib.ExchangeRateQueryService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.frizo.grpc.api.ExchangeRate.ExchangeRateReq,
      com.frizo.grpc.api.ExchangeRate.ExchangeRateResp> getGetExchangeRateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getExchangeRate",
      requestType = com.frizo.grpc.api.ExchangeRate.ExchangeRateReq.class,
      responseType = com.frizo.grpc.api.ExchangeRate.ExchangeRateResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.frizo.grpc.api.ExchangeRate.ExchangeRateReq,
      com.frizo.grpc.api.ExchangeRate.ExchangeRateResp> getGetExchangeRateMethod() {
    io.grpc.MethodDescriptor<com.frizo.grpc.api.ExchangeRate.ExchangeRateReq, com.frizo.grpc.api.ExchangeRate.ExchangeRateResp> getGetExchangeRateMethod;
    if ((getGetExchangeRateMethod = ExchangeRateQueryServiceGrpc.getGetExchangeRateMethod) == null) {
      synchronized (ExchangeRateQueryServiceGrpc.class) {
        if ((getGetExchangeRateMethod = ExchangeRateQueryServiceGrpc.getGetExchangeRateMethod) == null) {
          ExchangeRateQueryServiceGrpc.getGetExchangeRateMethod = getGetExchangeRateMethod =
              io.grpc.MethodDescriptor.<com.frizo.grpc.api.ExchangeRate.ExchangeRateReq, com.frizo.grpc.api.ExchangeRate.ExchangeRateResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getExchangeRate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.frizo.grpc.api.ExchangeRate.ExchangeRateReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.frizo.grpc.api.ExchangeRate.ExchangeRateResp.getDefaultInstance()))
              .setSchemaDescriptor(new ExchangeRateQueryServiceMethodDescriptorSupplier("getExchangeRate"))
              .build();
        }
      }
    }
    return getGetExchangeRateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ExchangeRateQueryServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExchangeRateQueryServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExchangeRateQueryServiceStub>() {
        @java.lang.Override
        public ExchangeRateQueryServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExchangeRateQueryServiceStub(channel, callOptions);
        }
      };
    return ExchangeRateQueryServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ExchangeRateQueryServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExchangeRateQueryServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExchangeRateQueryServiceBlockingStub>() {
        @java.lang.Override
        public ExchangeRateQueryServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExchangeRateQueryServiceBlockingStub(channel, callOptions);
        }
      };
    return ExchangeRateQueryServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ExchangeRateQueryServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ExchangeRateQueryServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ExchangeRateQueryServiceFutureStub>() {
        @java.lang.Override
        public ExchangeRateQueryServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ExchangeRateQueryServiceFutureStub(channel, callOptions);
        }
      };
    return ExchangeRateQueryServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static abstract class ExchangeRateQueryServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void getExchangeRate(com.frizo.grpc.api.ExchangeRate.ExchangeRateReq request,
        io.grpc.stub.StreamObserver<com.frizo.grpc.api.ExchangeRate.ExchangeRateResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetExchangeRateMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetExchangeRateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.frizo.grpc.api.ExchangeRate.ExchangeRateReq,
                com.frizo.grpc.api.ExchangeRate.ExchangeRateResp>(
                  this, METHODID_GET_EXCHANGE_RATE)))
          .build();
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class ExchangeRateQueryServiceStub extends io.grpc.stub.AbstractAsyncStub<ExchangeRateQueryServiceStub> {
    private ExchangeRateQueryServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExchangeRateQueryServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExchangeRateQueryServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public void getExchangeRate(com.frizo.grpc.api.ExchangeRate.ExchangeRateReq request,
        io.grpc.stub.StreamObserver<com.frizo.grpc.api.ExchangeRate.ExchangeRateResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetExchangeRateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class ExchangeRateQueryServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ExchangeRateQueryServiceBlockingStub> {
    private ExchangeRateQueryServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExchangeRateQueryServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExchangeRateQueryServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.frizo.grpc.api.ExchangeRate.ExchangeRateResp getExchangeRate(com.frizo.grpc.api.ExchangeRate.ExchangeRateReq request) {
      return blockingUnaryCall(
          getChannel(), getGetExchangeRateMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * The greeting service definition.
   * </pre>
   */
  public static final class ExchangeRateQueryServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ExchangeRateQueryServiceFutureStub> {
    private ExchangeRateQueryServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExchangeRateQueryServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ExchangeRateQueryServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Sends a greeting
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.frizo.grpc.api.ExchangeRate.ExchangeRateResp> getExchangeRate(
        com.frizo.grpc.api.ExchangeRate.ExchangeRateReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetExchangeRateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_EXCHANGE_RATE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ExchangeRateQueryServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ExchangeRateQueryServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_EXCHANGE_RATE:
          serviceImpl.getExchangeRate((com.frizo.grpc.api.ExchangeRate.ExchangeRateReq) request,
              (io.grpc.stub.StreamObserver<com.frizo.grpc.api.ExchangeRate.ExchangeRateResp>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ExchangeRateQueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ExchangeRateQueryServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.frizo.grpc.api.ExchangeRate.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ExchangeRateQueryService");
    }
  }

  private static final class ExchangeRateQueryServiceFileDescriptorSupplier
      extends ExchangeRateQueryServiceBaseDescriptorSupplier {
    ExchangeRateQueryServiceFileDescriptorSupplier() {}
  }

  private static final class ExchangeRateQueryServiceMethodDescriptorSupplier
      extends ExchangeRateQueryServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ExchangeRateQueryServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ExchangeRateQueryServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ExchangeRateQueryServiceFileDescriptorSupplier())
              .addMethod(getGetExchangeRateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
