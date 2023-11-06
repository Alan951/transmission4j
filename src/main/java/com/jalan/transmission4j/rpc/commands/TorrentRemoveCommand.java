package com.jalan.transmission4j.rpc.commands;

import com.jalan.transmission4j.models.torrent.TorrentRemoveInfo;
import com.jalan.transmission4j.rpc.RpcCommand;

public class TorrentRemoveCommand extends RpcCommand<TorrentRemoveInfo, Object> {
    
    @Override
    public String getMethod() {
        return "torrent-remove";
    }

}
