package com.erojas.reactive.mapper;

import com.erojas.reactive.entity.TrackEntity;
import com.erojas.reactive.model.Track;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 23:25
 * @FileName : AlbumMapper.java
 * @Description : Mapper track
 */
@Mapper
public interface TrackMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    TrackEntity apiToEntity(Track api);

    Track entityToApi(TrackEntity api);

    List<TrackEntity> apiToEntity(List<Track> api);

    List<Track> entityToApi(List<TrackEntity> api);
}
