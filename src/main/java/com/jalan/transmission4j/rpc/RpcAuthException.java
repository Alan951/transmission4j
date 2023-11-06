package com.jalan.transmission4j.rpc;

public class RpcAuthException extends Throwable {
    
    public String getMessage() {
        return "Authenticatin required for RPC connections";
    }

}
