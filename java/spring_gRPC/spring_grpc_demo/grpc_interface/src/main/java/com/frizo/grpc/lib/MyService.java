// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: example.proto

package com.frizo.grpc.lib;

/**
 * <pre>
 * The greeting service definition.
 * </pre>
 *
 * Protobuf service {@code com.frizo.grpc.lib.MyService}
 */
public  abstract class MyService
    implements com.google.protobuf.Service {
  protected MyService() {}

  public interface Interface {
    /**
     * <pre>
     * Sends a greeting
     * </pre>
     *
     * <code>rpc SayHello(.com.frizo.grpc.lib.HelloRequest) returns (.com.frizo.grpc.lib.HelloReply);</code>
     */
    public abstract void sayHello(
        com.google.protobuf.RpcController controller,
        com.frizo.grpc.lib.HelloRequest request,
        com.google.protobuf.RpcCallback<com.frizo.grpc.lib.HelloReply> done);

  }

  public static com.google.protobuf.Service newReflectiveService(
      final Interface impl) {
    return new MyService() {
      @java.lang.Override
      public  void sayHello(
          com.google.protobuf.RpcController controller,
          com.frizo.grpc.lib.HelloRequest request,
          com.google.protobuf.RpcCallback<com.frizo.grpc.lib.HelloReply> done) {
        impl.sayHello(controller, request, done);
      }

    };
  }

  public static com.google.protobuf.BlockingService
      newReflectiveBlockingService(final BlockingInterface impl) {
    return new com.google.protobuf.BlockingService() {
      public final com.google.protobuf.Descriptors.ServiceDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }

      public final com.google.protobuf.Message callBlockingMethod(
          com.google.protobuf.Descriptors.MethodDescriptor method,
          com.google.protobuf.RpcController controller,
          com.google.protobuf.Message request)
          throws com.google.protobuf.ServiceException {
        if (method.getService() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "Service.callBlockingMethod() given method descriptor for " +
            "wrong service type.");
        }
        switch(method.getIndex()) {
          case 0:
            return impl.sayHello(controller, (com.frizo.grpc.lib.HelloRequest)request);
          default:
            throw new java.lang.AssertionError("Can't get here.");
        }
      }

      public final com.google.protobuf.Message
          getRequestPrototype(
          com.google.protobuf.Descriptors.MethodDescriptor method) {
        if (method.getService() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "Service.getRequestPrototype() given method " +
            "descriptor for wrong service type.");
        }
        switch(method.getIndex()) {
          case 0:
            return com.frizo.grpc.lib.HelloRequest.getDefaultInstance();
          default:
            throw new java.lang.AssertionError("Can't get here.");
        }
      }

      public final com.google.protobuf.Message
          getResponsePrototype(
          com.google.protobuf.Descriptors.MethodDescriptor method) {
        if (method.getService() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "Service.getResponsePrototype() given method " +
            "descriptor for wrong service type.");
        }
        switch(method.getIndex()) {
          case 0:
            return com.frizo.grpc.lib.HelloReply.getDefaultInstance();
          default:
            throw new java.lang.AssertionError("Can't get here.");
        }
      }

    };
  }

  /**
   * <pre>
   * Sends a greeting
   * </pre>
   *
   * <code>rpc SayHello(.com.frizo.grpc.lib.HelloRequest) returns (.com.frizo.grpc.lib.HelloReply);</code>
   */
  public abstract void sayHello(
      com.google.protobuf.RpcController controller,
      com.frizo.grpc.lib.HelloRequest request,
      com.google.protobuf.RpcCallback<com.frizo.grpc.lib.HelloReply> done);

  public static final
      com.google.protobuf.Descriptors.ServiceDescriptor
      getDescriptor() {
    return com.frizo.grpc.lib.HelloWorldProto.getDescriptor().getServices().get(0);
  }
  public final com.google.protobuf.Descriptors.ServiceDescriptor
      getDescriptorForType() {
    return getDescriptor();
  }

  public final void callMethod(
      com.google.protobuf.Descriptors.MethodDescriptor method,
      com.google.protobuf.RpcController controller,
      com.google.protobuf.Message request,
      com.google.protobuf.RpcCallback<
        com.google.protobuf.Message> done) {
    if (method.getService() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "Service.callMethod() given method descriptor for wrong " +
        "service type.");
    }
    switch(method.getIndex()) {
      case 0:
        this.sayHello(controller, (com.frizo.grpc.lib.HelloRequest)request,
          com.google.protobuf.RpcUtil.<com.frizo.grpc.lib.HelloReply>specializeCallback(
            done));
        return;
      default:
        throw new java.lang.AssertionError("Can't get here.");
    }
  }

  public final com.google.protobuf.Message
      getRequestPrototype(
      com.google.protobuf.Descriptors.MethodDescriptor method) {
    if (method.getService() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "Service.getRequestPrototype() given method " +
        "descriptor for wrong service type.");
    }
    switch(method.getIndex()) {
      case 0:
        return com.frizo.grpc.lib.HelloRequest.getDefaultInstance();
      default:
        throw new java.lang.AssertionError("Can't get here.");
    }
  }

  public final com.google.protobuf.Message
      getResponsePrototype(
      com.google.protobuf.Descriptors.MethodDescriptor method) {
    if (method.getService() != getDescriptor()) {
      throw new java.lang.IllegalArgumentException(
        "Service.getResponsePrototype() given method " +
        "descriptor for wrong service type.");
    }
    switch(method.getIndex()) {
      case 0:
        return com.frizo.grpc.lib.HelloReply.getDefaultInstance();
      default:
        throw new java.lang.AssertionError("Can't get here.");
    }
  }

  public static Stub newStub(
      com.google.protobuf.RpcChannel channel) {
    return new Stub(channel);
  }

  public static final class Stub extends com.frizo.grpc.lib.MyService implements Interface {
    private Stub(com.google.protobuf.RpcChannel channel) {
      this.channel = channel;
    }

    private final com.google.protobuf.RpcChannel channel;

    public com.google.protobuf.RpcChannel getChannel() {
      return channel;
    }

    public  void sayHello(
        com.google.protobuf.RpcController controller,
        com.frizo.grpc.lib.HelloRequest request,
        com.google.protobuf.RpcCallback<com.frizo.grpc.lib.HelloReply> done) {
      channel.callMethod(
        getDescriptor().getMethods().get(0),
        controller,
        request,
        com.frizo.grpc.lib.HelloReply.getDefaultInstance(),
        com.google.protobuf.RpcUtil.generalizeCallback(
          done,
          com.frizo.grpc.lib.HelloReply.class,
          com.frizo.grpc.lib.HelloReply.getDefaultInstance()));
    }
  }

  public static BlockingInterface newBlockingStub(
      com.google.protobuf.BlockingRpcChannel channel) {
    return new BlockingStub(channel);
  }

  public interface BlockingInterface {
    public com.frizo.grpc.lib.HelloReply sayHello(
        com.google.protobuf.RpcController controller,
        com.frizo.grpc.lib.HelloRequest request)
        throws com.google.protobuf.ServiceException;
  }

  private static final class BlockingStub implements BlockingInterface {
    private BlockingStub(com.google.protobuf.BlockingRpcChannel channel) {
      this.channel = channel;
    }

    private final com.google.protobuf.BlockingRpcChannel channel;

    public com.frizo.grpc.lib.HelloReply sayHello(
        com.google.protobuf.RpcController controller,
        com.frizo.grpc.lib.HelloRequest request)
        throws com.google.protobuf.ServiceException {
      return (com.frizo.grpc.lib.HelloReply) channel.callBlockingMethod(
        getDescriptor().getMethods().get(0),
        controller,
        request,
        com.frizo.grpc.lib.HelloReply.getDefaultInstance());
    }

  }

  // @@protoc_insertion_point(class_scope:com.frizo.grpc.lib.MyService)
}

