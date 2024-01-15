package com.frizo.common;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@Slf4j
public abstract class AbstractEventMethodAdaptor<T extends EventMethod> {

    protected EventMethodRegister<T> eventMethodRegister;

    public AbstractEventMethodAdaptor(EventMethodRegister<T> eventMethodRegister){
        this.eventMethodRegister = eventMethodRegister;
    }

    // 從註冊表 get EventMethod
    public T getEventMethod(String eventMethodName) {
        return eventMethodRegister.get(eventMethodName);
    }

    public Set<String> getAllEventMethodName(){
        return eventMethodRegister.getAllEventMethodName();
    }

}
