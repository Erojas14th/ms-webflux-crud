package com.erojas.reactive.service;

import com.erojas.reactive.enums.HttpErrorEnum;
import com.erojas.reactive.exception.InternalErrorException;
import com.erojas.reactive.exception.NotFoundException;
import com.erojas.reactive.mapper.AlbumMapper;
import com.erojas.reactive.model.Album;
import com.erojas.reactive.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static reactor.core.publisher.Mono.error;

/**
 * @Author : erojas
 * @Datecreation : 1/01/2022 12:16
 * @FileName : AlbumServiceImpl.java
 * @Description : Implementacion de AlbumService
 */
@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private AlbumMapper albumMapper;


    /**
     * Metodo para obtener un coleccion de álbumes.
     *
     * @param artistId
     * @return Flux<Album>
     * @throws NotFoundException
     */
    public Flux<Album> get(Integer artistId) {
        return albumRepository
                .findAlbumEntitiesByArtistId(artistId)
                .switchIfEmpty(error(new NotFoundException(String.format(HttpErrorEnum.NOT_FOUND_ALBUM.getMessage(), artistId))))
                .map(albumMapper::entityToApi);
    }

    /**
     * Metodo para obtener un coleccion de álbumes.
     *
     * @param artistId
     * @return Flux<Album>
     * @throws NotFoundException
     */
    public Mono<Album> get(Integer artistId, Integer albumId) {
        return this.get(artistId)
                .filter(obj -> obj.getAlbumId().intValue() == albumId.intValue())
                .single();
    }


    /**
     * Metodo para persistir un integrante del artista/banda.
     *
     * @param album
     * @return Mono<Album>
     * @throws DataIntegrityViolationException
     */
    public Mono<Album> save(Album album) {
        return Mono.just(album)
                .map(albumMapper::apiToEntity)
                .flatMap(albumRepository::save)
                .onErrorMap(DataIntegrityViolationException.class, ex -> new InternalErrorException(String.format(HttpErrorEnum.INTERNAL_ERROR_ALBUM_DUPLICATE.getMessage(), album.getAlbumId())))
                .map(albumMapper::entityToApi);
    }

    /**
     * Metodo para persistir un integrante del artista/banda.
     *
     * @param albums
     * @return Mono<Album>
     * @throws DataIntegrityViolationException
     */
    public Flux<Album> save(List<Album> albums) {
        return Flux.fromIterable(albums)
                .flatMap(this::save);
    }

    /**
     * Metodo para eliminar un album
     *
     * @param artistId
     * @return Mono<Void>
     */
    public Mono<Void> delete(Integer artistId) {
        return albumRepository.deleteAlbumEntitiesByArtistId(artistId);
    }

    /**
     * Metodo para eliminar un album
     *
     * @param artistId
     * @param albumId
     * @return Mono<Void>
     */
    public Mono<Void> delete(Integer artistId, Integer albumId) {
        return albumRepository.deleteAlbumEntityByArtistIdAndAlbumId(artistId, albumId);
    }


}
