package com.frizo.grpc.server.base;

import com.frizo.grpc.api.ExchangeRateQueryServiceGrpc.*;
import com.frizo.grpc.api.ExchangeRate.*;
import com.frizo.grpc.server.service.BussinessModelService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExchangeRateQueryServiceBase extends  ExchangeRateQueryServiceImplBase {

    @Autowired
    private BussinessModelService bussinessModelService;

    public void getExchangeRate(ExchangeRateReq request, StreamObserver<ExchangeRateResp> responseObserver){
        ExchangeRateResp response = bussinessModelService.queryExchangeRate(request);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
