package com.erojas.reactive.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:22
 * @FileName : ArtistEntity.java
 * @Description : Pojo Artist
 */
@Data
@NoArgsConstructor
public class Artist {

    private Integer artistId;
    private String name;
    private List<Album> albums = new ArrayList<>();
    private List<Person> persons = new ArrayList<>();
}
