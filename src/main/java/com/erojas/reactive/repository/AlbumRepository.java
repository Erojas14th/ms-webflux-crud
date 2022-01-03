package com.erojas.reactive.repository;

import com.erojas.reactive.entity.AlbumEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:45
 * @FileName : AlbumRepository.java
 * @Description : AlbumRepository
 */
@Repository
public interface AlbumRepository extends ReactiveCrudRepository<AlbumEntity, String> {
    Flux<AlbumEntity> findAlbumEntitiesByArtistId(Integer artistId);

    Mono<Void> deleteAlbumEntitiesByArtistId(Integer artistId);

    Mono<Void> deleteAlbumEntityByArtistIdAndAlbumId(Integer artistId, Integer albumId);
}
