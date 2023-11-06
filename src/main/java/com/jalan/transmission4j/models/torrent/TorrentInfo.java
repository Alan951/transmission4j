package com.jalan.transmission4j.models.torrent;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TorrentInfo {

    private Integer id;
    private Boolean isFinished;
    private String magnetLink;
    private String name;
    private Double percentComplete;
    private TorrentInfoStatus status;
    private String hashString;

    public List<String> getInstanceVariableNames() {
        Field[] fields = this.getClass().getDeclaredFields();

        return Arrays.asList(fields).stream().map(field -> field.getName()).collect(Collectors.toList());
    }
}
