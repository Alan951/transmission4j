package com.jalan.transmission4j.rpc;

import java.io.IOException;

import com.jalan.transmission4j.models.torrent.TorrentAddRequest;
import com.jalan.transmission4j.models.torrent.TorrentAddResult;
import com.jalan.transmission4j.models.torrent.TorrentInfo;
import com.jalan.transmission4j.models.torrent.TorrentInfoRequest;
import com.jalan.transmission4j.models.torrent.TorrentInfoResult;
import com.jalan.transmission4j.models.torrent.TorrentRemoveInfo;
import com.jalan.transmission4j.rpc.commands.TorrentAddCommand;
import com.jalan.transmission4j.rpc.commands.TorrentGetCommand;
import com.jalan.transmission4j.rpc.commands.TorrentRemoveCommand;

public class TransmissionService {
    
    private static TransmissionService service;
    private RpcRequester requester;

    private TransmissionService(RpcRequester requester) {
        this.requester = requester;
    }

    public static TransmissionService getInstance(RpcRequester requester) {
        if(service == null) {
            service = new TransmissionService(requester);
        }

        return service;
    }

    public TorrentInfoResult getTorrentInfo() throws IOException {
        TorrentGetCommand command = new TorrentGetCommand();
        command.setPayload(new TorrentInfoRequest(new TorrentInfo().getInstanceVariableNames()));
        return this.requester.request(command).getResponse().getArguments();
    }

    public TorrentInfoResult getTorrentInfoByIds(TorrentInfoRequest torrentInfoRequest) throws IOException {
        TorrentGetCommand command = new TorrentGetCommand();
        command.setPayload(torrentInfoRequest);
        return this.requester.request(command).getResponse().getArguments();
    }

    public TorrentAddResult addTorrent(String filename) throws IOException {
        TorrentAddCommand command = new TorrentAddCommand();
        command.setPayload(new TorrentAddRequest(filename));
        return this.requester.request(command).getResponse().getArguments();
    }

    public Object removeTorrent(TorrentRemoveInfo torrentRemoveInfo) throws IOException {
        TorrentRemoveCommand command = new TorrentRemoveCommand();
        command.setPayload(torrentRemoveInfo);
        return this.requester.request(command).getResponse().getArguments();
    }
}
