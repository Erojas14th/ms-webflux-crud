package com.erojas.reactive.service;

import com.erojas.reactive.model.Artist;
import reactor.core.publisher.Mono;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 23:10
 * @FileName : ArtistService.java
 * @Description : ArtistService
 */
public interface ArtistService {
    Mono<Artist> get(Integer artistId);

    Mono<Artist> save(Artist artist);

    Mono<Void> delete(Integer artistId);
}
