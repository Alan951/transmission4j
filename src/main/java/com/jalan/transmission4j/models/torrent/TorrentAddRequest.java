package com.jalan.transmission4j.models.torrent;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TorrentAddRequest {
    private String filename;
    @JsonProperty("download-dir")
    private String downloadDir;
    private String metainfo;
    private boolean paused;
    @JsonProperty("peer-limit")
    private Integer peerLimit;
    private String cookies;
    private String[] labels;
    private Integer bandwithPriority;
    
    public TorrentAddRequest(String filename) {
        this.filename = filename;
    }
    
    
}
