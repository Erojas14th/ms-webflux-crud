package com.erojas.reactive.service;

import com.erojas.reactive.enums.HttpErrorEnum;
import com.erojas.reactive.exception.InternalErrorException;
import com.erojas.reactive.exception.NotFoundException;
import com.erojas.reactive.mapper.ArtistMapper;
import com.erojas.reactive.model.Artist;
import com.erojas.reactive.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static reactor.core.publisher.Mono.error;
/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 23:11
 * @FileName : ArtistServiceImpl.java
 * @Description : Implementacion de ArtistService
 */
@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private ArtistMapper artistMapper;

    /**
     * Metodo para obtener un artista/banda.
     *
     * @param artistId
     * @return Flux<Artist>
     * @throws NotFoundException
     */
    public Mono<Artist> get(Integer artistId) {
        return artistRepository
                .findByArtistId(artistId)
                .switchIfEmpty(error(new NotFoundException(String.format(HttpErrorEnum.NOT_FOUND_ARTIST.getMessage(),artistId))))
                .map(artistMapper::entityToApi);
    }


    /**
     * Metodo para persistir un artista/banda.
     *
     * @param artist
     * @return Mono<Artist>
     * @throws DataIntegrityViolationException
     */
    public Mono<Artist> save(Artist artist) {
        return Mono.just(artist)
                .map(artistMapper::apiToEntity)
                .flatMap(artistRepository::save)
                .onErrorMap(DataIntegrityViolationException.class,ex -> new InternalErrorException(String.format(HttpErrorEnum.INTERNAL_ERROR_ARTIST_DUPLICATE.getMessage(), artist.getArtistId())))
                .map(artistMapper::entityToApi);
    }

    /**
     * Metodo para eliminar un artista
     *
     * @param artistId
     * @return Mono<Void>
     */
    public Mono<Void> delete(Integer artistId) {
        return artistRepository.deleteByArtistId(artistId);
    }
}
