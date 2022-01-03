package com.erojas.reactive.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:32
 * @FileName : AlbumEntity.java
 * @Description : Pojo Album
 */
@Data
@NoArgsConstructor
public class Album {

    private Integer albumId;
    private Integer artistId;
    private String name;
    private Integer year;
    private List<Track> tracks = new ArrayList<>();
}
