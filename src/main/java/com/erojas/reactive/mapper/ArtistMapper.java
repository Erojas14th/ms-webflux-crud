package com.erojas.reactive.mapper;

import com.erojas.reactive.entity.ArtistEntity;
import com.erojas.reactive.model.Artist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 23:22
 * @FileName : ArtistMapper.java
 * @Description : Mapper artist
 */
@Mapper
public interface ArtistMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    ArtistEntity apiToEntity(Artist api);

    Artist entityToApi(ArtistEntity entity);
}
