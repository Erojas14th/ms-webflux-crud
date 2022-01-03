package com.erojas.reactive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:28
 * @FileName : PersonEntity.java
 * @Description : Collection personEntities
 */
@Data
@Document(collection = "persons")
public class PersonEntity {

    @Id
    private String id;
    @Version
    private int version;

    @Indexed(unique = true)
    private Integer personId;
    private Integer artistId;
    private String name;
    private String job;

}
