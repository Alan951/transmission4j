package com.jalan.transmission4j.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RpcResponse <T> {
    
    private T arguments;
    private String result;
    private Long tag;

}
