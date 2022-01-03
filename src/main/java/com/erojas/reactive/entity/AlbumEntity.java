package com.erojas.reactive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:32
 * @FileName : AlbumEntity.java
 * @Description : Collection albumEntities
 */
@Data
@Document(collection = "albums")
public class AlbumEntity {

    @Id
    private String id;
    @Version
    private int version;
    @Indexed(unique = true)
    private Integer albumId;
    private Integer artistId;
    private String name;
    private Integer year;
}
