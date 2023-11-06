package com.jalan.transmission4j.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest <T extends Object> {
    
    private String method;
    private T arguments;
    private Integer tag;

}
