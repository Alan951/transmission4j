package com.jalan.transmission4j.models.torrent;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jalan.transmission4j.models.torrent.ids.Ids;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TorrentRemoveInfo {

    private Ids ids;

    @JsonProperty
    private Boolean deleteLocalData;

    public Object getIds() {
        if(ids == null) {
            return null;
        }

        return ids.theObject();
    }

    public Boolean getDeleteLocalData() {
        return deleteLocalData;
    }
}
