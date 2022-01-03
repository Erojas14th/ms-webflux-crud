package com.erojas.reactive.initial;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 15:35
 * @FileName : MonoTest.java
 * @Description : Pruebas unitarias para error
 */
public class ErrorTest {

    @Test
    public void validateCreateError() {
        Mono<String> mono = Mono.error(new NoSuchFieldException());
        String name = mono.onErrorReturn("Barbora").block();
        assertEquals("Barbora",name);
    }

    @Test
    public void validateCreateMono() {
        Mono<String> mono = Mono.just("Juan");
        String result = mono.flatMap(obj -> Mono.just(obj.substring(1, 20)))
                .doOnError(s -> System.out.println("Error occured!"))
                .onErrorReturn("Ju")
                .switchIfEmpty(Mono.just("Juan"))
                .block();

        assertEquals("Ju", result);
    }


}
