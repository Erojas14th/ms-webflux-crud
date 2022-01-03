package com.erojas.reactive.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:33
 * @FileName : TrackEntity.java
 * @Description : Pojo Track
 */
@Data
@Builder
public class Track {

    private Integer trackId;
    private Integer albumId;
    private Integer order;
    private String name;
    private String time;

}
