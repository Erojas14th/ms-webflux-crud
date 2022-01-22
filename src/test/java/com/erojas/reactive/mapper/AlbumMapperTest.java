package com.erojas.reactive.mapper;

import com.erojas.reactive.entity.AlbumEntity;
import com.erojas.reactive.model.Album;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : Edgar Solano Rojas
 * @datecreation : 21/01/2022 22:19
 * @fileName : AlbumMapperTest.java
 * @description : PU para albumMapper
 */
public class AlbumMapperTest {

    private final AlbumMapper mapper = Mappers.getMapper(AlbumMapper.class);

    @Test
    public void validateAlbum() {
        Album album = new Album();
        album.setArtistId(1);
        album.setAlbumId(1);
        album.setName("Test");
        album.setYear(2021);
        album.setTracks(new ArrayList<>());

        AlbumEntity entity = mapper.apiToEntity(album);
        assertTrue(validateValues(entity,album));

        Album album1 = mapper.entityToApi(entity);
        assertTrue(validateValues(entity,album1));
    }

    @Test
    public void validateAlbums() {
        Album album = new Album();
        album.setArtistId(1);
        album.setAlbumId(1);
        album.setName("Test");
        album.setYear(2021);
        album.setTracks(new ArrayList<>());

        List<AlbumEntity> entity = mapper.apiToEntity(Collections.singletonList(album));
        assertTrue(validateValues(entity.get(0),album));

        List<Album> album1 = mapper.entityToApi(entity);
        assertTrue(validateValues(entity.get(0),album1.get(0)));
    }

    public boolean validateValues(AlbumEntity expected, Album current){
        return expected.getAlbumId().intValue() == current.getAlbumId().intValue()
                && expected.getArtistId().intValue() == current.getArtistId().intValue()
                && expected.getName().equalsIgnoreCase(current.getName())
                && expected.getYear().intValue() == current.getYear().intValue();
    }

}
