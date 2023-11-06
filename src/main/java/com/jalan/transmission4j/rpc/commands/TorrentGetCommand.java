package com.jalan.transmission4j.rpc.commands;

import com.jalan.transmission4j.models.torrent.TorrentInfoRequest;
import com.jalan.transmission4j.models.torrent.TorrentInfoResult;
import com.jalan.transmission4j.rpc.RpcCommand;

public class TorrentGetCommand extends RpcCommand<TorrentInfoRequest, TorrentInfoResult> {

    @Override
    public String getMethod() {
        return "torrent-get";
    }

}
