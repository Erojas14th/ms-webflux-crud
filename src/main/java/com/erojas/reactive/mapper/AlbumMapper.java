package com.erojas.reactive.mapper;

import com.erojas.reactive.entity.AlbumEntity;
import com.erojas.reactive.model.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 23:25
 * @FileName : AlbumMapper.java
 * @Description : Mapper album
 */
@Mapper
public interface AlbumMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    AlbumEntity apiToEntity(Album api);

    Album entityToApi(AlbumEntity api);

    List<AlbumEntity> apiToEntity(List<Album> api);

    List<Album> entityToApi(List<AlbumEntity> api);
}
