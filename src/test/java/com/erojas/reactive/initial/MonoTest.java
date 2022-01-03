package com.erojas.reactive.initial;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 15:35
 * @FileName : MonoTest.java
 * @Description : Pruebas unitarias para mono
 */
public class MonoTest {

    @Test
    public void validateCreateMono(){
        Mono<String> mono = Mono.just("Juan");
        String result = mono.block();
        assertEquals("Juan",result);
    }

    @Test
    public void validateCreateMonoNull(){

        assertThrows(NullPointerException.class,()->{
            Mono<String> mono = Mono.just(null);
            String result = mono.block();
            assertEquals(null,result);
        });

        Mono<String> mono1 = Mono.justOrEmpty(null);
        String result1 = mono1.block();
        assertEquals(null,result1);

        Mono<String> mono2 = Mono.justOrEmpty(null);
        Optional<String> result2 = mono2.blockOptional();
        assertFalse(result2.isPresent());
    }


    @Test
    public void validateCreateMonoFailed(){
        Mono<String> mono = Mono.error(new NoSuchFieldException());
        String name = mono.onErrorReturn("Barbora").block();
        assertEquals("Barbora",name);
    }
}
