// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: exchangeRate.proto

package com.gash.application.grpc.exchangerate;

public interface ExchangeRateReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:com.gash.application.grpc.exchangerate.ExchangeRateReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string currencyCode = 1;</code>
   * @return The currencyCode.
   */
  String getCurrencyCode();
  /**
   * <code>string currencyCode = 1;</code>
   * @return The bytes for currencyCode.
   */
  com.google.protobuf.ByteString
      getCurrencyCodeBytes();

  /**
   * <code>string exchangeCurrencyCode = 2;</code>
   * @return The exchangeCurrencyCode.
   */
  String getExchangeCurrencyCode();
  /**
   * <code>string exchangeCurrencyCode = 2;</code>
   * @return The bytes for exchangeCurrencyCode.
   */
  com.google.protobuf.ByteString
      getExchangeCurrencyCodeBytes();
}
