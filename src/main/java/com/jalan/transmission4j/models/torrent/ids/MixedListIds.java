package com.jalan.transmission4j.models.torrent.ids;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "ids")
public class MixedListIds extends Ids {

    private final List<Object> ids;

    public MixedListIds(List<Object> ids) {
        this.ids = ids;
    }

    public List<Object> getIds() {
        return this.ids;
    }

    @Override
    public Object theObject(){
        return ids;
    }

}
