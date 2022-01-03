package com.erojas.reactive.service;

import com.erojas.reactive.enums.HttpErrorEnum;
import com.erojas.reactive.exception.InternalErrorException;
import com.erojas.reactive.exception.NotFoundException;
import com.erojas.reactive.mapper.TrackMapper;
import com.erojas.reactive.model.Track;
import com.erojas.reactive.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static reactor.core.publisher.Mono.error;

/**
 * @Author : erojas
 * @Datecreation : 1/01/2022 12:21
 * @FileName : TrackServiceImpl.java
 * @Description : Implementacion de TrackService
 */
@Service
public class TrackServiceImpl implements TrackService {

    @Autowired
    private TrackRepository trackRepository;
    @Autowired
    private TrackMapper trackMapper;

    /**
     * Metodo para obtener una coleccion de integrantes del artista/banda.
     *
     * @return Flux<Track>
     */
    public Flux<Track> findAll() {
        return trackRepository
                .findAll()
                .map(trackMapper::entityToApi);
    }

    /**
     * Metodo para obtener un coleccion de integrantes del artista/banda.
     *
     * @param albumId
     * @return Flux<Track>
     * @throws NotFoundException
     */
    public Flux<Track> get(Integer albumId) {
        return trackRepository
                .findAllByAlbumId(albumId)
                .log()
                .switchIfEmpty(error(new NotFoundException(String.format(HttpErrorEnum.NOT_FOUND_TRACK.getMessage(), albumId))))
                .map(trackMapper::entityToApi);
    }


    /**
     * Metodo para obtener un coleccion de integrantes del artista/banda.
     *
     * @param albumId
     * @return Flux<Track>
     * @throws NotFoundException
     */
    public Mono<Track> get(Integer albumId, Integer trackId) {
        return this.get(albumId)
                .filter(obj -> obj.getTrackId().intValue() == trackId.intValue())
                .single();
    }

    /**
     * Metodo para persistir un integrante del artista/banda.
     *
     * @param track
     * @return Mono<Track>
     * @throws DataIntegrityViolationException
     */
    public Mono<Track> save(Track track) {
        return Mono.just(track)
                .map(trackMapper::apiToEntity)
                .flatMap(trackRepository::save)
                .onErrorMap(DataIntegrityViolationException.class, ex -> new InternalErrorException(String.format(HttpErrorEnum.INTERNAL_ERROR_TRACK_DUPLICATE.getMessage(), track.getTrackId())))
                .map(trackMapper::entityToApi);
    }

    /**
     * Metodo para persistir un track del album.
     *
     * @param tracks
     * @return Mono<Track>
     * @throws DataIntegrityViolationException
     */
    public Flux<Track> save(List<Track> tracks) {
        return Flux.fromIterable(tracks)
                .flatMap(this::save);
    }

    /**
     * Metodo para eliminar un track del album.
     *
     * @param albumId
     * @return Mono<Void>
     */
    public Mono<Void> delete(Integer albumId) {
        return trackRepository.deleteTrackEntityByAlbumId(albumId);
    }

    /**
     * Metodo para eliminar un track del album.
     *
     * @param albumId
     * @param trackId
     * @return Mono<Void>
     */
    public Mono<Void> delete(Integer albumId, Integer trackId) {
        return trackRepository.deleteByAlbumIdAndTrackId(albumId, trackId);
    }




}
