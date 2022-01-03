package com.erojas.reactive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:22
 * @FileName : ArtistEntity.java
 * @Description : Collection artists
 */
@Data
@Document(collection = "artists")
public class ArtistEntity {

    @Id
    private String id;
    @Version
    private int version;
    @Indexed(unique = true)
    private Integer artistId;
    private String name;

}
