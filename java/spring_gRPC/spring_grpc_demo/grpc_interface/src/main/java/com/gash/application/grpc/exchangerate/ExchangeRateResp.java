// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: exchangeRate.proto

package com.gash.application.grpc.exchangerate;

/**
 * <pre>
 * The response message containing the greetings
 * </pre>
 *
 * Protobuf type {@code com.gash.application.grpc.exchangerate.ExchangeRateResp}
 */
public  final class ExchangeRateResp extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:com.gash.application.grpc.exchangerate.ExchangeRateResp)
    ExchangeRateRespOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ExchangeRateResp.newBuilder() to construct.
  private ExchangeRateResp(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ExchangeRateResp() {
    msgCode_ = "";
    currencyCode_ = "";
    exchangeCurrencyCode_ = "";
    price_ = "";
    priceHigh24H_ = "";
    priceLow24H_ = "";
  }

  @Override
  @SuppressWarnings({"unused"})
  protected Object newInstance(
      UnusedPrivateParameter unused) {
    return new ExchangeRateResp();
  }

  @Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ExchangeRateResp(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            String s = input.readStringRequireUtf8();

            msgCode_ = s;
            break;
          }
          case 18: {
            String s = input.readStringRequireUtf8();

            currencyCode_ = s;
            break;
          }
          case 26: {
            String s = input.readStringRequireUtf8();

            exchangeCurrencyCode_ = s;
            break;
          }
          case 34: {
            String s = input.readStringRequireUtf8();

            price_ = s;
            break;
          }
          case 42: {
            String s = input.readStringRequireUtf8();

            priceHigh24H_ = s;
            break;
          }
          case 50: {
            String s = input.readStringRequireUtf8();

            priceLow24H_ = s;
            break;
          }
          case 56: {

            unixTime_ = input.readInt64();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.gash.application.grpc.exchangerate.ExchangeRate.internal_static_com_gash_application_grpc_exchangerate_ExchangeRateResp_descriptor;
  }

  @Override
  protected FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.gash.application.grpc.exchangerate.ExchangeRate.internal_static_com_gash_application_grpc_exchangerate_ExchangeRateResp_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.gash.application.grpc.exchangerate.ExchangeRateResp.class, com.gash.application.grpc.exchangerate.ExchangeRateResp.Builder.class);
  }

  public static final int MSGCODE_FIELD_NUMBER = 1;
  private volatile Object msgCode_;
  /**
   * <pre>
   * 000000 成功 99999 失敗
   * </pre>
   *
   * <code>string msgCode = 1;</code>
   * @return The msgCode.
   */
  public String getMsgCode() {
    Object ref = msgCode_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      msgCode_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * 000000 成功 99999 失敗
   * </pre>
   *
   * <code>string msgCode = 1;</code>
   * @return The bytes for msgCode.
   */
  public com.google.protobuf.ByteString
      getMsgCodeBytes() {
    Object ref = msgCode_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      msgCode_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CURRENCYCODE_FIELD_NUMBER = 2;
  private volatile Object currencyCode_;
  /**
   * <code>string currencyCode = 2;</code>
   * @return The currencyCode.
   */
  public String getCurrencyCode() {
    Object ref = currencyCode_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      currencyCode_ = s;
      return s;
    }
  }
  /**
   * <code>string currencyCode = 2;</code>
   * @return The bytes for currencyCode.
   */
  public com.google.protobuf.ByteString
      getCurrencyCodeBytes() {
    Object ref = currencyCode_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      currencyCode_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EXCHANGECURRENCYCODE_FIELD_NUMBER = 3;
  private volatile Object exchangeCurrencyCode_;
  /**
   * <code>string exchangeCurrencyCode = 3;</code>
   * @return The exchangeCurrencyCode.
   */
  public String getExchangeCurrencyCode() {
    Object ref = exchangeCurrencyCode_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      exchangeCurrencyCode_ = s;
      return s;
    }
  }
  /**
   * <code>string exchangeCurrencyCode = 3;</code>
   * @return The bytes for exchangeCurrencyCode.
   */
  public com.google.protobuf.ByteString
      getExchangeCurrencyCodeBytes() {
    Object ref = exchangeCurrencyCode_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      exchangeCurrencyCode_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PRICE_FIELD_NUMBER = 4;
  private volatile Object price_;
  /**
   * <code>string price = 4;</code>
   * @return The price.
   */
  public String getPrice() {
    Object ref = price_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      price_ = s;
      return s;
    }
  }
  /**
   * <code>string price = 4;</code>
   * @return The bytes for price.
   */
  public com.google.protobuf.ByteString
      getPriceBytes() {
    Object ref = price_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      price_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PRICEHIGH24H_FIELD_NUMBER = 5;
  private volatile Object priceHigh24H_;
  /**
   * <code>string priceHigh24h = 5;</code>
   * @return The priceHigh24h.
   */
  public String getPriceHigh24H() {
    Object ref = priceHigh24H_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      priceHigh24H_ = s;
      return s;
    }
  }
  /**
   * <code>string priceHigh24h = 5;</code>
   * @return The bytes for priceHigh24h.
   */
  public com.google.protobuf.ByteString
      getPriceHigh24HBytes() {
    Object ref = priceHigh24H_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      priceHigh24H_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PRICELOW24H_FIELD_NUMBER = 6;
  private volatile Object priceLow24H_;
  /**
   * <code>string priceLow24h = 6;</code>
   * @return The priceLow24h.
   */
  public String getPriceLow24H() {
    Object ref = priceLow24H_;
    if (ref instanceof String) {
      return (String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      String s = bs.toStringUtf8();
      priceLow24H_ = s;
      return s;
    }
  }
  /**
   * <code>string priceLow24h = 6;</code>
   * @return The bytes for priceLow24h.
   */
  public com.google.protobuf.ByteString
      getPriceLow24HBytes() {
    Object ref = priceLow24H_;
    if (ref instanceof String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (String) ref);
      priceLow24H_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int UNIXTIME_FIELD_NUMBER = 7;
  private long unixTime_;
  /**
   * <code>int64 unixTime = 7;</code>
   * @return The unixTime.
   */
  public long getUnixTime() {
    return unixTime_;
  }

  private byte memoizedIsInitialized = -1;
  @Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getMsgCodeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, msgCode_);
    }
    if (!getCurrencyCodeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, currencyCode_);
    }
    if (!getExchangeCurrencyCodeBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, exchangeCurrencyCode_);
    }
    if (!getPriceBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 4, price_);
    }
    if (!getPriceHigh24HBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 5, priceHigh24H_);
    }
    if (!getPriceLow24HBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 6, priceLow24H_);
    }
    if (unixTime_ != 0L) {
      output.writeInt64(7, unixTime_);
    }
    unknownFields.writeTo(output);
  }

  @Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getMsgCodeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, msgCode_);
    }
    if (!getCurrencyCodeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, currencyCode_);
    }
    if (!getExchangeCurrencyCodeBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, exchangeCurrencyCode_);
    }
    if (!getPriceBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, price_);
    }
    if (!getPriceHigh24HBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, priceHigh24H_);
    }
    if (!getPriceLow24HBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, priceLow24H_);
    }
    if (unixTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(7, unixTime_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @Override
  public boolean equals(final Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.gash.application.grpc.exchangerate.ExchangeRateResp)) {
      return super.equals(obj);
    }
    com.gash.application.grpc.exchangerate.ExchangeRateResp other = (com.gash.application.grpc.exchangerate.ExchangeRateResp) obj;

    if (!getMsgCode()
        .equals(other.getMsgCode())) return false;
    if (!getCurrencyCode()
        .equals(other.getCurrencyCode())) return false;
    if (!getExchangeCurrencyCode()
        .equals(other.getExchangeCurrencyCode())) return false;
    if (!getPrice()
        .equals(other.getPrice())) return false;
    if (!getPriceHigh24H()
        .equals(other.getPriceHigh24H())) return false;
    if (!getPriceLow24H()
        .equals(other.getPriceLow24H())) return false;
    if (getUnixTime()
        != other.getUnixTime()) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + MSGCODE_FIELD_NUMBER;
    hash = (53 * hash) + getMsgCode().hashCode();
    hash = (37 * hash) + CURRENCYCODE_FIELD_NUMBER;
    hash = (53 * hash) + getCurrencyCode().hashCode();
    hash = (37 * hash) + EXCHANGECURRENCYCODE_FIELD_NUMBER;
    hash = (53 * hash) + getExchangeCurrencyCode().hashCode();
    hash = (37 * hash) + PRICE_FIELD_NUMBER;
    hash = (53 * hash) + getPrice().hashCode();
    hash = (37 * hash) + PRICEHIGH24H_FIELD_NUMBER;
    hash = (53 * hash) + getPriceHigh24H().hashCode();
    hash = (37 * hash) + PRICELOW24H_FIELD_NUMBER;
    hash = (53 * hash) + getPriceLow24H().hashCode();
    hash = (37 * hash) + UNIXTIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUnixTime());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.gash.application.grpc.exchangerate.ExchangeRateResp parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.gash.application.grpc.exchangerate.ExchangeRateResp prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @Override
  protected Builder newBuilderForType(
      BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * The response message containing the greetings
   * </pre>
   *
   * Protobuf type {@code com.gash.application.grpc.exchangerate.ExchangeRateResp}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:com.gash.application.grpc.exchangerate.ExchangeRateResp)
      com.gash.application.grpc.exchangerate.ExchangeRateRespOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.gash.application.grpc.exchangerate.ExchangeRate.internal_static_com_gash_application_grpc_exchangerate_ExchangeRateResp_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.gash.application.grpc.exchangerate.ExchangeRate.internal_static_com_gash_application_grpc_exchangerate_ExchangeRateResp_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.gash.application.grpc.exchangerate.ExchangeRateResp.class, com.gash.application.grpc.exchangerate.ExchangeRateResp.Builder.class);
    }

    // Construct using com.gash.application.grpc.exchangerate.ExchangeRateResp.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @Override
    public Builder clear() {
      super.clear();
      msgCode_ = "";

      currencyCode_ = "";

      exchangeCurrencyCode_ = "";

      price_ = "";

      priceHigh24H_ = "";

      priceLow24H_ = "";

      unixTime_ = 0L;

      return this;
    }

    @Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.gash.application.grpc.exchangerate.ExchangeRate.internal_static_com_gash_application_grpc_exchangerate_ExchangeRateResp_descriptor;
    }

    @Override
    public com.gash.application.grpc.exchangerate.ExchangeRateResp getDefaultInstanceForType() {
      return getDefaultInstance();
    }

    @Override
    public com.gash.application.grpc.exchangerate.ExchangeRateResp build() {
      com.gash.application.grpc.exchangerate.ExchangeRateResp result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @Override
    public com.gash.application.grpc.exchangerate.ExchangeRateResp buildPartial() {
      com.gash.application.grpc.exchangerate.ExchangeRateResp result = new com.gash.application.grpc.exchangerate.ExchangeRateResp(this);
      result.msgCode_ = msgCode_;
      result.currencyCode_ = currencyCode_;
      result.exchangeCurrencyCode_ = exchangeCurrencyCode_;
      result.price_ = price_;
      result.priceHigh24H_ = priceHigh24H_;
      result.priceLow24H_ = priceLow24H_;
      result.unixTime_ = unixTime_;
      onBuilt();
      return result;
    }

    @Override
    public Builder clone() {
      return super.clone();
    }
    @Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.setField(field, value);
    }
    @Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return super.addRepeatedField(field, value);
    }
    @Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.gash.application.grpc.exchangerate.ExchangeRateResp) {
        return mergeFrom((com.gash.application.grpc.exchangerate.ExchangeRateResp)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.gash.application.grpc.exchangerate.ExchangeRateResp other) {
      if (other == getDefaultInstance()) return this;
      if (!other.getMsgCode().isEmpty()) {
        msgCode_ = other.msgCode_;
        onChanged();
      }
      if (!other.getCurrencyCode().isEmpty()) {
        currencyCode_ = other.currencyCode_;
        onChanged();
      }
      if (!other.getExchangeCurrencyCode().isEmpty()) {
        exchangeCurrencyCode_ = other.exchangeCurrencyCode_;
        onChanged();
      }
      if (!other.getPrice().isEmpty()) {
        price_ = other.price_;
        onChanged();
      }
      if (!other.getPriceHigh24H().isEmpty()) {
        priceHigh24H_ = other.priceHigh24H_;
        onChanged();
      }
      if (!other.getPriceLow24H().isEmpty()) {
        priceLow24H_ = other.priceLow24H_;
        onChanged();
      }
      if (other.getUnixTime() != 0L) {
        setUnixTime(other.getUnixTime());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @Override
    public final boolean isInitialized() {
      return true;
    }

    @Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.gash.application.grpc.exchangerate.ExchangeRateResp parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.gash.application.grpc.exchangerate.ExchangeRateResp) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private Object msgCode_ = "";
    /**
     * <pre>
     * 000000 成功 99999 失敗
     * </pre>
     *
     * <code>string msgCode = 1;</code>
     * @return The msgCode.
     */
    public String getMsgCode() {
      Object ref = msgCode_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        msgCode_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <pre>
     * 000000 成功 99999 失敗
     * </pre>
     *
     * <code>string msgCode = 1;</code>
     * @return The bytes for msgCode.
     */
    public com.google.protobuf.ByteString
        getMsgCodeBytes() {
      Object ref = msgCode_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        msgCode_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * 000000 成功 99999 失敗
     * </pre>
     *
     * <code>string msgCode = 1;</code>
     * @param value The msgCode to set.
     * @return This builder for chaining.
     */
    public Builder setMsgCode(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      msgCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 000000 成功 99999 失敗
     * </pre>
     *
     * <code>string msgCode = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearMsgCode() {
      
      msgCode_ = getDefaultInstance().getMsgCode();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * 000000 成功 99999 失敗
     * </pre>
     *
     * <code>string msgCode = 1;</code>
     * @param value The bytes for msgCode to set.
     * @return This builder for chaining.
     */
    public Builder setMsgCodeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      msgCode_ = value;
      onChanged();
      return this;
    }

    private Object currencyCode_ = "";
    /**
     * <code>string currencyCode = 2;</code>
     * @return The currencyCode.
     */
    public String getCurrencyCode() {
      Object ref = currencyCode_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        currencyCode_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string currencyCode = 2;</code>
     * @return The bytes for currencyCode.
     */
    public com.google.protobuf.ByteString
        getCurrencyCodeBytes() {
      Object ref = currencyCode_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        currencyCode_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string currencyCode = 2;</code>
     * @param value The currencyCode to set.
     * @return This builder for chaining.
     */
    public Builder setCurrencyCode(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      currencyCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string currencyCode = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearCurrencyCode() {
      
      currencyCode_ = getDefaultInstance().getCurrencyCode();
      onChanged();
      return this;
    }
    /**
     * <code>string currencyCode = 2;</code>
     * @param value The bytes for currencyCode to set.
     * @return This builder for chaining.
     */
    public Builder setCurrencyCodeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      currencyCode_ = value;
      onChanged();
      return this;
    }

    private Object exchangeCurrencyCode_ = "";
    /**
     * <code>string exchangeCurrencyCode = 3;</code>
     * @return The exchangeCurrencyCode.
     */
    public String getExchangeCurrencyCode() {
      Object ref = exchangeCurrencyCode_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        exchangeCurrencyCode_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string exchangeCurrencyCode = 3;</code>
     * @return The bytes for exchangeCurrencyCode.
     */
    public com.google.protobuf.ByteString
        getExchangeCurrencyCodeBytes() {
      Object ref = exchangeCurrencyCode_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        exchangeCurrencyCode_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string exchangeCurrencyCode = 3;</code>
     * @param value The exchangeCurrencyCode to set.
     * @return This builder for chaining.
     */
    public Builder setExchangeCurrencyCode(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      exchangeCurrencyCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string exchangeCurrencyCode = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearExchangeCurrencyCode() {
      
      exchangeCurrencyCode_ = getDefaultInstance().getExchangeCurrencyCode();
      onChanged();
      return this;
    }
    /**
     * <code>string exchangeCurrencyCode = 3;</code>
     * @param value The bytes for exchangeCurrencyCode to set.
     * @return This builder for chaining.
     */
    public Builder setExchangeCurrencyCodeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      exchangeCurrencyCode_ = value;
      onChanged();
      return this;
    }

    private Object price_ = "";
    /**
     * <code>string price = 4;</code>
     * @return The price.
     */
    public String getPrice() {
      Object ref = price_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        price_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string price = 4;</code>
     * @return The bytes for price.
     */
    public com.google.protobuf.ByteString
        getPriceBytes() {
      Object ref = price_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        price_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string price = 4;</code>
     * @param value The price to set.
     * @return This builder for chaining.
     */
    public Builder setPrice(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      price_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string price = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearPrice() {
      
      price_ = getDefaultInstance().getPrice();
      onChanged();
      return this;
    }
    /**
     * <code>string price = 4;</code>
     * @param value The bytes for price to set.
     * @return This builder for chaining.
     */
    public Builder setPriceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      price_ = value;
      onChanged();
      return this;
    }

    private Object priceHigh24H_ = "";
    /**
     * <code>string priceHigh24h = 5;</code>
     * @return The priceHigh24h.
     */
    public String getPriceHigh24H() {
      Object ref = priceHigh24H_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        priceHigh24H_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string priceHigh24h = 5;</code>
     * @return The bytes for priceHigh24h.
     */
    public com.google.protobuf.ByteString
        getPriceHigh24HBytes() {
      Object ref = priceHigh24H_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        priceHigh24H_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string priceHigh24h = 5;</code>
     * @param value The priceHigh24h to set.
     * @return This builder for chaining.
     */
    public Builder setPriceHigh24H(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      priceHigh24H_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string priceHigh24h = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearPriceHigh24H() {
      
      priceHigh24H_ = getDefaultInstance().getPriceHigh24H();
      onChanged();
      return this;
    }
    /**
     * <code>string priceHigh24h = 5;</code>
     * @param value The bytes for priceHigh24h to set.
     * @return This builder for chaining.
     */
    public Builder setPriceHigh24HBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      priceHigh24H_ = value;
      onChanged();
      return this;
    }

    private Object priceLow24H_ = "";
    /**
     * <code>string priceLow24h = 6;</code>
     * @return The priceLow24h.
     */
    public String getPriceLow24H() {
      Object ref = priceLow24H_;
      if (!(ref instanceof String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        priceLow24H_ = s;
        return s;
      } else {
        return (String) ref;
      }
    }
    /**
     * <code>string priceLow24h = 6;</code>
     * @return The bytes for priceLow24h.
     */
    public com.google.protobuf.ByteString
        getPriceLow24HBytes() {
      Object ref = priceLow24H_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        priceLow24H_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string priceLow24h = 6;</code>
     * @param value The priceLow24h to set.
     * @return This builder for chaining.
     */
    public Builder setPriceLow24H(
        String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      priceLow24H_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string priceLow24h = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearPriceLow24H() {
      
      priceLow24H_ = getDefaultInstance().getPriceLow24H();
      onChanged();
      return this;
    }
    /**
     * <code>string priceLow24h = 6;</code>
     * @param value The bytes for priceLow24h to set.
     * @return This builder for chaining.
     */
    public Builder setPriceLow24HBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      priceLow24H_ = value;
      onChanged();
      return this;
    }

    private long unixTime_ ;
    /**
     * <code>int64 unixTime = 7;</code>
     * @return The unixTime.
     */
    public long getUnixTime() {
      return unixTime_;
    }
    /**
     * <code>int64 unixTime = 7;</code>
     * @param value The unixTime to set.
     * @return This builder for chaining.
     */
    public Builder setUnixTime(long value) {
      
      unixTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 unixTime = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearUnixTime() {
      
      unixTime_ = 0L;
      onChanged();
      return this;
    }
    @Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:com.gash.application.grpc.exchangerate.ExchangeRateResp)
  }

  // @@protoc_insertion_point(class_scope:com.gash.application.grpc.exchangerate.ExchangeRateResp)
  private static final com.gash.application.grpc.exchangerate.ExchangeRateResp DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.gash.application.grpc.exchangerate.ExchangeRateResp();
  }

  public static com.gash.application.grpc.exchangerate.ExchangeRateResp getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ExchangeRateResp>
      PARSER = new com.google.protobuf.AbstractParser<ExchangeRateResp>() {
    @Override
    public ExchangeRateResp parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ExchangeRateResp(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ExchangeRateResp> parser() {
    return PARSER;
  }

  @Override
  public com.google.protobuf.Parser<ExchangeRateResp> getParserForType() {
    return PARSER;
  }

  @Override
  public com.gash.application.grpc.exchangerate.ExchangeRateResp getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

