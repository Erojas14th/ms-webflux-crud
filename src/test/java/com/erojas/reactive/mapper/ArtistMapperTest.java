package com.erojas.reactive.mapper;

import com.erojas.reactive.entity.AlbumEntity;
import com.erojas.reactive.entity.ArtistEntity;
import com.erojas.reactive.model.Album;
import com.erojas.reactive.model.Artist;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author : Edgar Solano Rojas
 * @datecreation : 21/01/2022 23:41
 * @fileName : ArtistMapperTest.java
 * @description : PU de artist
 */
public class ArtistMapperTest {

    private final ArtistMapper mapper = Mappers.getMapper(ArtistMapper.class);

    @Test
    public void validateAlbum() {
        Artist artist = new Artist();
        artist.setArtistId(1);
        artist.setName("Test");

        ArtistEntity entity = mapper.apiToEntity(artist);
        assertTrue(validateValues(entity,artist));

        Artist artist1 = mapper.entityToApi(entity);
        assertTrue(validateValues(entity,artist1));
    }


    public boolean validateValues(ArtistEntity expected, Artist current){
        return expected.getArtistId().intValue() == current.getArtistId().intValue()
                && expected.getName().equalsIgnoreCase(current.getName());
    }

}
