package com.erojas.reactive.initial;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 15:35
 * @FileName : MonoTest.java
 * @Description : Pruebas unitarias para flux
 */
public class SubscribeEventTest {

    @Test
    public void validateCreateFluxFrom() {
        Mono<String> mono = Mono.just("Hello, world!");
        String result = mono
                .doOnTerminate(() -> System.out.println("Mono was terminated !"))
                .doOnError(t -> System.out.println("Error result!"))
                .doOnSuccess(s -> System.out.println("Successful result!"))
                .block();
        assertEquals("Hello, world!",mono.block());
    }


}
