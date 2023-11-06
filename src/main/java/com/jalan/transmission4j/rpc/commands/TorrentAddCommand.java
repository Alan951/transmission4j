package com.jalan.transmission4j.rpc.commands;

import com.jalan.transmission4j.models.torrent.TorrentAddRequest;
import com.jalan.transmission4j.models.torrent.TorrentAddResult;
import com.jalan.transmission4j.rpc.RpcCommand;

public class TorrentAddCommand extends RpcCommand<TorrentAddRequest, TorrentAddResult> {

    @Override
    public String getMethod() {
        return "torrent-add";
    }
}
