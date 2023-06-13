package com.frizo.schedule;

import com.google.gson.Gson;
import lombok.Data;

import java.util.Map;

@Data
public class EventResult {
    private static final Gson GSON = new Gson();

    private Boolean result;

    private String requestJson;

    private String responseJson;

    private String exceptionJson;

    public void addExceptionMsg(String errorMsg){
        this.exceptionJson = GSON.toJson(Map.of("error", errorMsg));
    }

    public void addResultMsg(String resultMsg){
        this.responseJson = GSON.toJson(Map.of("result", resultMsg));
    }

    public void addRequestJson(Object request){
        this.requestJson = GSON.toJson(request);
    }

    public void addResponseJson(Object response){
        this.requestJson = GSON.toJson(response);
    }

}
