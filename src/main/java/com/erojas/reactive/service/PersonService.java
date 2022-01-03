package com.erojas.reactive.service;

import com.erojas.reactive.model.Person;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @Author : erojas
 * @Datecreation : 1/01/2022 12:07
 * @FileName : PersonService.java
 * @Description : PersonService
 */
public interface PersonService {

    Flux<Person> get(Integer artistId);

    Mono<Person> get(Integer artistId, Integer personId);

    Mono<Person> save(Person person);

    Flux<Person> save(List<Person> person);

    Mono<Void> delete(Integer artistId, Integer personId);

    Mono<Void> delete(Integer artistId);
}
