package com.jalan.transmission4j.models.torrent;

public enum TorrentInfoStatus {
    STOPPED(0),
    QUEUED_VERIFY_DATA(1),
    VERIFYING_DATA(2),
    QUEUED_DOWNLOAD(3),
    DOWNLOADING(4),
    QUEUED_SEED(5),
    SEDDING(6);

    public final Integer statusCode;

    TorrentInfoStatus(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
