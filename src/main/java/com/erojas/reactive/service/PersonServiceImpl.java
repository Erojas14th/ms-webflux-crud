package com.erojas.reactive.service;

import com.erojas.reactive.enums.HttpErrorEnum;
import com.erojas.reactive.exception.InternalErrorException;
import com.erojas.reactive.exception.NotFoundException;
import com.erojas.reactive.mapper.PersonMapper;
import com.erojas.reactive.model.Person;
import com.erojas.reactive.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static reactor.core.publisher.Mono.error;

/**
 * @Author : erojas
 * @Datecreation : 1/01/2022 12:09
 * @FileName : PersonServiceImpl.java
 * @Description : Implementacion de PersonService
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private PersonMapper personMapper;

    /**
     * Metodo para obtener un coleccion de integrantes del artista/banda.
     *
     * @param artistId
     * @return Flux<Person>
     * @throws NotFoundException
     */
    public Flux<Person> get(Integer artistId) {
        return personRepository
                .findAllByArtistId(artistId)
                .switchIfEmpty(error(new NotFoundException(String.format(HttpErrorEnum.NOT_FOUND_PERSON.getMessage(), artistId))))
                .map(personMapper::entityToApi);
    }

    /**
     * Metodo para persistir un integrante del artista/banda.
     *
     * @param person
     * @return Mono<Person>
     * @throws DataIntegrityViolationException
     */
    public Mono<Person> save(Person person) {
        return Mono.just(person)
                .map(personMapper::apiToEntity)
                .flatMap(personRepository::save)
                .onErrorMap(DataIntegrityViolationException.class, ex -> new InternalErrorException(String.format(HttpErrorEnum.INTERNAL_ERROR_PERSON_DUPLICATE.getMessage(), person.getPersonId())))
                .map(personMapper::entityToApi);
    }

    /**
     * Metodo para persistir una coleccion de  integrantes del artista/banda.
     *
     * @param persons
     * @return Flux<Person>
     * @throws DataIntegrityViolationException
     */
    public Flux<Person> save(List<Person> persons) {
        return Flux.fromIterable(persons)
                .flatMap(this::save);


    }

    /**
     * Metodo para obtener un coleccion de integrantes del artista/banda.
     *
     * @param artistId
     * @param personId
     * @return Flux<Person>
     * @throws NotFoundException
     */
    public Mono<Person> get(Integer artistId, Integer personId) {
        return this.get(artistId).filter(obj -> obj.getPersonId().intValue() == personId.intValue()).single();
    }

    /**
     * Metodo para eliminar un integrante de la banda
     *
     * @param artistId
     * @param personId
     * @return Mono<Void>
     */
    public Mono<Void> delete(Integer artistId, Integer personId) {
        return personRepository.deleteByArtistIdAndPersonId(artistId, personId);
    }

    /**
     * Metodo para eliminar un integrante de la banda
     *
     * @param artistId
     * @return Mono<Void>
     */
    public Mono<Void> delete(Integer artistId) {
        return personRepository.deleteByArtistId(artistId);
    }
}
