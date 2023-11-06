package com.jalan.transmission4j.models.torrent;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TorrentAddResult {
    
    @JsonProperty("torrent-added")
    private TorrentInfo torrentAdded;

    @JsonProperty("torrent-duplicate")
    private TorrentInfo torrentDuplicate;

}
