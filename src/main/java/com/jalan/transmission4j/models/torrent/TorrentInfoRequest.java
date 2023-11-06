package com.jalan.transmission4j.models.torrent;

import java.util.List;

import com.jalan.transmission4j.models.torrent.ids.Ids;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TorrentInfoRequest {
    
    private List<String> fields;
    private Ids ids;

    public TorrentInfoRequest(List<String> fields) {
        this.fields = fields;
    }

    public TorrentInfoRequest(Ids ids) {
        this.fields = new TorrentInfo().getInstanceVariableNames();
        this.ids = ids;
    }

    public Object getIds() {
        if(ids == null) return null;

        return ids.theObject();
    }

}
