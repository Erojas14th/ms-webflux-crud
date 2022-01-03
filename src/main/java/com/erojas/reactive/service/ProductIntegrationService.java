package com.erojas.reactive.service;

import com.erojas.reactive.model.Album;
import com.erojas.reactive.model.Artist;
import com.erojas.reactive.model.Person;
import com.erojas.reactive.model.Track;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author : erojas
 * @Datecreation : 1/01/2022 13:23
 * @FileName : ProductIntegrationService.java
 * @Description : Representa el componente de integracion
 */
@Component
@Transactional
public class ProductIntegrationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductIntegrationService.class);

    @Autowired
    private ArtistService artistService;
    @Autowired
    private PersonService personService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private TrackService trackService;

    /**
     * Metodo para obtener un artista compuesto por:
     * Persons.
     * Album.
     * Track
     *
     * @param artistId
     * @return
     */
    public Mono<Artist> get(Integer artistId) {
        Mono<Artist> artist = artistService.get(artistId);
        Mono<List<Person>> persons = personService.get(artistId).collectList();
        Mono<List<Album>> albums = albumService.get(artistId).collectList();
        Mono<List<Track>> tracksTemp = trackService.findAll().collectList();
        Mono<Map<Integer, List<Track>>> tracks = Mono.zip(values -> createCompositeTracks((List<Album>) values[0], (List<Track>) values[1]), albums, tracksTemp);
        return Mono.zip(values -> createCompositeArtist((Artist) values[0], (List<Person>) values[1], (List<Album>) values[2], (Map<Integer, List<Track>>) values[3]), artist, persons, albums, tracks);
    }

    /**
     * Metodo para obtener los tracks compuesto por el id del album
     *
     * @param albums
     * @param tracks
     * @return
     */
    private Map<Integer, List<Track>> createCompositeTracks(List<Album> albums, List<Track> tracks) {
        Map<Integer, List<Track>> result = new HashMap<>();
        albums.forEach(currentAlbum -> {
            List<Track> currentTrack = tracks.stream().filter(obj -> obj.getAlbumId().intValue() == currentAlbum.getAlbumId().intValue()).collect(Collectors.toList());
            result.put(currentAlbum.getAlbumId(), currentTrack);
        });

        return result;
    }


    /**
     * Metodo para persistir un artista/banda
     *
     * @param artistParam
     * @return Mono<Artist>
     */
    public Mono<Artist> save(Artist artistParam) {
        Mono<Artist> artist = artistService.save(artistParam);
        Mono<List<Person>> persons = personService.save(artistParam.getPersons()).collectList();
        Mono<List<Album>> albums = albumService.save(artistParam.getAlbums()).collectList();
        Mono<List<Album>> albumsTemp = Flux.fromIterable(artistParam.getAlbums()).collectList();
        Mono<Map<Integer, List<Track>>> tracks = Mono.zip(values -> createCompositeTracks((List<Album>) values[0]), albumsTemp);
        return Mono.zip(values -> createCompositeArtist((Artist) values[0], (List<Person>) values[1], (List<Album>) values[2], (Map<Integer, List<Track>>) values[3]), artist, persons, albums, tracks);
    }

    /**
     * Metodo para obtener los track por album
     *
     * @param albums
     * @return
     */
    private Map<Integer, List<Track>> createCompositeTracks(List<Album> albums) {
        Map<Integer, List<Track>> result = new HashMap<>();
        albums.forEach(currentAlbum -> {
            List<Track> trackList = new ArrayList<>();
            Flux<Track> trackFlux = trackService.save(currentAlbum.getTracks());
            trackFlux.subscribe(trackList::add);
            result.put(currentAlbum.getAlbumId(), trackList);
        });
        return result;
    }


    /**
     * Metodo para eliminar.
     * Artist
     * Person
     * Album
     * Black
     *
     * @param artistId
     * @return
     */
    public Mono<Void> delete(Integer artistId) {
        Mono<Void> artist = artistService.delete(artistId);
        Mono<Void> person = personService.delete(artistId);
        Mono<Void> album = albumService.delete(artistId);
        Mono<Void> track = trackService.delete(artistId);
        return Mono.zip(values -> deleteCompositeArtist((Void) values[0], (Void) values[1], (Void) values[2], (Void) values[3]), artist, person, album, track);

    }

    /**
     * Metodo para eliminar.
     *
     * @param artist
     * @param person
     * @param album
     * @param track
     * @return
     */
    private Void deleteCompositeArtist(Void artist, Void person, Void album, Void track) {
        return track;
    }


    /**
     * Metodo para crear artista compuesto
     *
     * @param artistParam
     * @param personsParam
     * @param albumsParam
     * @return
     */
    private Artist createCompositeArtist(Artist artistParam, List<Person> personsParam, List<Album> albumsParam, Map<Integer, List<Track>> tracks) {

        Artist artist = artistParam;
        artist.setPersons(personsParam);

        List<Album> albums = new ArrayList<>();
        List<Track> tracksOrdered = new ArrayList<>();

        albumsParam.forEach(currentAlbum -> {

            tracks.forEach((k, v) -> {
                Integer key = (Integer) k;
                if (Boolean.TRUE.equals(currentAlbum.getAlbumId() == key.intValue())) {
                    List<Track> value = (List<Track>) v;
                    currentAlbum.setTracks(value);
                }
            });

            currentAlbum.getTracks().stream().sorted(Comparator.comparing(Track::getOrder));
            albums.add(currentAlbum);
        });

        artist.setAlbums(albums);
        return artist;
    }


}
