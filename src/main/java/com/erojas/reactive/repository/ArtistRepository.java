package com.erojas.reactive.repository;

import com.erojas.reactive.entity.ArtistEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:44
 * @FileName : ArtistRepository.java
 * @Description : ArtistRepository
 */
@Repository
public interface ArtistRepository extends ReactiveCrudRepository<ArtistEntity, String> {
    Mono<ArtistEntity> findByArtistId(Integer artistId);

    Mono<Void> deleteByArtistId(Integer artistId);
}
