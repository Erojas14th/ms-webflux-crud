package com.erojas.reactive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:33
 * @FileName : TrackEntity.java
 * @Description : Collection tracks
 */
@Data
@Document(collection = "tracks")
public class TrackEntity {
    @Id
    private String id;
    @Version
    private int version;
    @Indexed(unique = true)
    private Integer trackId;
    private Integer albumId;
    private Integer order;
    private String name;
    private String time;

}
