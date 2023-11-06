package com.jalan.transmission4j.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransmissionConfig {
    
    private String url;
    private String username;
    private String password;

    public TransmissionConfig(String url) {
        this.url = url;
    }

}
