package com.jalan.transmission4j.models.torrent;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TorrentInfoResult {
    
    private List<TorrentInfo> torrents;

}
