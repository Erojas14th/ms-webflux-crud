package com.erojas.reactive.controller;

import com.erojas.reactive.model.Album;
import com.erojas.reactive.model.Artist;
import com.erojas.reactive.service.ProductIntegrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author : Edgar Solano Rojas
 * @datecreation : 21/01/2022 22:38
 * @fileName : ArtistControllerTest.java
 * @description : PU controller
 */
@SpringBootTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class ArtistControllerTest {

    private final Integer ARTIST_ID_OK=1;
    private WebClient client;

    @MockBean
    private ProductIntegrationService integration;

    @BeforeEach
    public void setUp() {
        client = WebClient.create();

        Mockito.when(integration.save(this.getArtist(ARTIST_ID_OK)))
                .thenReturn(Mono.just(this.getArtist(ARTIST_ID_OK)));

        Mockito.when(integration.get(ARTIST_ID_OK))
                .thenReturn(Mono.just(this.getArtist(ARTIST_ID_OK)));
    }

    @Test
    public void validateSave(){

        Mono<ResponseEntity<Artist>> response = save(this.getArtist(ARTIST_ID_OK));
        response.subscribe(obj -> {
                    assertEquals(HttpStatus.OK,obj.getStatusCode());
                    assertEquals(ARTIST_ID_OK,obj.getBody().getArtistId());
                });
    }

    @Test
    public void validateGet(){

        Mono<ResponseEntity<Artist>> response = get(ARTIST_ID_OK);
        response.subscribe(obj -> {
            assertEquals(HttpStatus.OK,obj.getStatusCode());
            assertEquals(ARTIST_ID_OK,obj.getBody().getArtistId());
        });
    }


    /**
     * Metodo para obtener el response del POST.
     * @param artist
     * @return ResponseEntity<Album>
     */
    public Mono<ResponseEntity<Artist>> save(Artist artist) {

       return  client.post()
                .uri("/artist")
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(artist)
                .retrieve()
                .toEntity(Artist.class);

    }

    /**
     * Metodo para obtener el response del GET.
     * @param artistId
     * @return Mono<ResponseEntity<Artist>>
     */
    public Mono<ResponseEntity<Artist>> get(Integer artistId){
        return client.get()
                .uri("/artist?artistId="+artistId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Artist.class);
    }
    /**
     * Metodo para obtener un album.
     * @param artistId
     * @return
     */
    public Artist getArtist(Integer artistId){
        Artist artist = new Artist();
        artist.setArtistId(artistId);
        artist.setName("Test");
        return artist;
    }
}
