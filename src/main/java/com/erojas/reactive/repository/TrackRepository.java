package com.erojas.reactive.repository;

import com.erojas.reactive.entity.TrackEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:46
 * @FileName : TrackRepository.java
 * @Description : TrackRepository
 */
@Repository
public interface TrackRepository extends ReactiveCrudRepository<TrackEntity, String> {

    Flux<TrackEntity> findAllByAlbumId(Integer albumId);
    Mono<Void> deleteTrackEntityByAlbumId(Integer albumId);
    Mono<Void> deleteByAlbumIdAndTrackId(Integer albumId,Integer trackId);
}
