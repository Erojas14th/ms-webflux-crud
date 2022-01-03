package com.erojas.reactive.service;

import com.erojas.reactive.model.Album;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author : erojas
 * @Datecreation : 1/01/2022 12:15
 * @FileName : AlbumService.java
 * @Description : Service Album
 */
public interface AlbumService {
    Flux<Album> get(Integer artistId);

    Mono<Album> get(Integer artistId, Integer albumId);

    Mono<Album> save(Album album);

    Flux<Album> save(List<Album> albums);

    Mono<Void> delete(Integer artistId);

    Mono<Void> delete(Integer artistId, Integer albumId);

}
