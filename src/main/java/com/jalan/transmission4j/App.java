package com.jalan.transmission4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.jalan.transmission4j.models.torrent.TorrentAddResult;
import com.jalan.transmission4j.models.torrent.TorrentInfo;
import com.jalan.transmission4j.models.torrent.TorrentInfoRequest;
import com.jalan.transmission4j.models.torrent.TorrentRemoveInfo;
import com.jalan.transmission4j.models.torrent.ids.MixedListIds;
import com.jalan.transmission4j.rpc.RpcRequester;
import com.jalan.transmission4j.rpc.TransmissionConfig;
import com.jalan.transmission4j.rpc.TransmissionService;

/**
 * Hello world!
 *
 */
public class App {

    private TransmissionService service;

    public void viewTorrents() throws IOException {
        List<TorrentInfo> torrents = service.getTorrentInfo().getTorrents();

        if(torrents.isEmpty()) {
            System.out.println("no torrents added yet");
        }

        for(TorrentInfo info : service.getTorrentInfo().getTorrents()) {
            System.out.println("Torrent (" + info.getId() + "): " + info.getName() + " - " + info.getStatus() + " | Finished: " + info.getIsFinished() );
        }
    }

    public void addTorrent(String url) throws IOException{
        TorrentAddResult result = service.addTorrent(url);
        Integer id = 0;

        if(result.getTorrentAdded() != null) {
            System.out.println("torrent added: " + result.getTorrentAdded());
            id = result.getTorrentAdded().getId();
        } else if(result.getTorrentDuplicate() != null) {
            System.out.println("torrent duplicated: " + result.getTorrentDuplicate());
            id = result.getTorrentDuplicate().getId();
        } else {
            System.out.println("error on add torrent");
        }

        if(id != null)
            System.out.println(service.getTorrentInfoByIds(new TorrentInfoRequest(new MixedListIds(Arrays.asList(id)))));
    }

    public void removeTorrent(Integer id) throws IOException {
        TorrentRemoveInfo torrentRemoveInfo = new TorrentRemoveInfo();
        torrentRemoveInfo.setIds(new MixedListIds(Arrays.asList(id)));
        torrentRemoveInfo.setDeleteLocalData(false);
    
        service.removeTorrent(torrentRemoveInfo);

        System.out.println("torrent removed!");
    }

    public void removeTorrent(String id) throws IOException {
        if(isNumber(id)) {
            removeTorrent(Integer.parseInt(id));
        } else{
            System.out.println("enter a valid number please");
        }
    }

    public boolean isNumber(String strNumber) {
        try{
            Integer.parseInt(strNumber);

            return true;
        } catch(NumberFormatException nfe) {
            return false;
        }
    }

    public static void main( String[] args ) throws IOException{
        TransmissionConfig config = new TransmissionConfig("http://pi:9091/transmission/rpc");

        if(System.getenv("transmission_user") != null && System.getenv("transmission_pass") != null) {
            config.setUsername(System.getenv("transmission_user"));
            config.setPassword(System.getenv("transmission_pass"));   
        } else {
            System.out.println("creds not found in env vars... add transmission_user and transmission_pass");
            System.exit(-1);
        }

        RpcRequester requester = new RpcRequester(config);
        TransmissionService service = TransmissionService.getInstance(requester);

        App app = new App();
        app.service = service;
        
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit) {
            System.out.println("select an option (only first letter of the options)");
            System.out.println("l)ist torrents");
            System.out.println("a)dd torrent");
            System.out.println("d)elete torrent using identifier");
            System.out.println("e)xit");

            String opt = scanner.nextLine();

            switch (opt) {
                case "l":
                    app.viewTorrents();
                    break;
                case "a":
                    System.out.println("enter a magnet link of torrent: ");
                    String url = scanner.nextLine();
                    app.addTorrent(url);
                    break;
                case "d":
                    System.out.println("enter the id of torrent");
                    String id = scanner.nextLine();
                    app.removeTorrent(id);
                    break;
                case "e":
                    exit = true;
                    break;
                default:
                    break;
            }
        }

        scanner.close();
    }
}
