package com.erojas.reactive.initial;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 15:35
 * @FileName : MonoTest.java
 * @Description : Pruebas unitarias para flux
 */
public class FluxTest {

    @Test
    public void validateCreateFluxFrom(){
        List<String> names = Arrays.asList("Angel","Carlos","Beto");
        Flux<String> flux = Flux.fromIterable(names);
        assertEquals("Angel",flux.blockFirst());
        assertEquals("Carlos",flux.elementAt(1).block());
        assertEquals("Beto",flux.blockLast());
    }

    @Test
    public void validateCreateFluxTo(){

        List<String> names = Arrays.asList("Angel","Carlos","Beto");
        Flux<String> flux = Flux.fromIterable(names);

        Iterable<String> strings1 = flux.toIterable();
        Stream<String> strings2 = flux.toStream();
        List<String> strings3 = flux.collectList().block();

        assertNotNull(strings1);
        assertNotNull(strings2);
        assertNotNull(strings3);
    }


}
