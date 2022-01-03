package com.erojas.reactive.service;

import com.erojas.reactive.model.Track;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author : erojas
 * @Datecreation : 1/01/2022 12:20
 * @FileName : TrackService.java
 * @Description : Servicio de Track
 */
public interface TrackService {

    Flux<Track> findAll();
    Flux<Track> get(Integer albumId);

    Mono<Track> get(Integer albumId, Integer trackId);

    Mono<Track> save(Track track);

    Flux<Track> save(List<Track> tracks);

    Mono<Void> delete(Integer albumId);

    Mono<Void> delete(Integer albumId, Integer trackId);


}
