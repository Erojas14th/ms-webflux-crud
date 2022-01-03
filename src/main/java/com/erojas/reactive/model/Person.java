package com.erojas.reactive.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:28
 * @FileName : PersonEntity.java
 * @Description : Pojo Person
 */
@Data
@Builder
public class Person {

    private Integer personId;
    private Integer artistId;
    private String name;
    private String job;
}
