package com.erojas.reactive.initial;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 15:35
 * @FileName : MonoTest.java
 * @Description : Pruebas unitarias para mapeo
 */
public class MapTest {

    @Test
    public void validateCreateMap(){
        Mono<String> mono = Mono.just("Juan");
        String result = mono.map(obj -> obj.substring(0,2)).block();
        assertEquals("Ju",result);
    }

    @Test
    public void validateCreateFlatMap(){
        Mono<String> mono = Mono.just("Juan");
        String result = mono.flatMap(obj -> Mono.just(obj.substring(0,2))).block();
        assertEquals("Ju",result);
    }
}
