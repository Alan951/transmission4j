package com.jalan.transmission4j.rpc;

import java.lang.reflect.ParameterizedType;

public abstract class RpcCommand<P, R> {
    
    private P payload;

    private RpcRequest<P> request;
    private RpcResponse<R> response;

    public abstract String getMethod();

    public RpcCommand() {
        request = new RpcRequest<P>();
    }

    public RpcRequest<P> buildRequestPayload() {
        request.setMethod(getMethod());
        request.setArguments(payload);
        return request;
    }

    public void setPayload(P payload) {
        this.payload = payload;
    }

    public void setResponse(RpcResponse<R> response) {
        this.response = response;
    }

    public RpcResponse<R> getResponse() {
        return this.response;
    }

    public Class getPayloadClazz() {
        return payload.getClass();
    }

    public Class getResponseClazz() {
        ParameterizedType genericClass = (ParameterizedType) getClass().getGenericSuperclass();
        return ((Class) genericClass.getActualTypeArguments()[1]);
    }

}
