package com.erojas.reactive.repository;

import com.erojas.reactive.entity.PersonEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 21:46
 * @FileName : PersonRepository.java
 * @Description : PersonRepository
 */
@Repository
public interface PersonRepository extends ReactiveCrudRepository<PersonEntity, String> {
    Flux<PersonEntity> findAllByArtistId(Integer artistId);

    Mono<PersonEntity> findByPersonId(Integer personId);

    Mono<Void> deleteByArtistIdAndPersonId(Integer artistId, Integer personId);
    Mono<Void> deleteByArtistId(Integer artistId);
}
