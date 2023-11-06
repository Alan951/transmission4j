package com.jalan.transmission4j.models.torrent;

import com.jalan.transmission4j.models.torrent.ids.Ids;

public class OmittedIds extends Ids {
    
    private Object nullObject;

    public Object getNullObject() {
        return nullObject;
    }

    @Override
    public Object theObject() {
        return nullObject;
    }

}
