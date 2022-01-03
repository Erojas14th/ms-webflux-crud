package com.erojas.reactive.initial;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 15:35
 * @FileName : MonoTest.java
 * @Description : Pruebas unitarias para merge
 */
public class MergeTest {

    @Test
    public void zipWith() {
        Mono<String> nameMono = Mono.just("Veronika");
        Mono<Double> gpaMono = Mono.just(3.5);
        Mono<Tuple2<String, Double>> zippedMono = nameMono.zipWith(gpaMono);
        Mono<String> result = zippedMono.map(obj -> tuple(obj.getT1(), obj.getT2()));
        assertEquals(tuple(nameMono.block(), gpaMono.block()), result.block());
    }

    public String tuple(String name, Double price) {
        return name + " - " + price;
    }

    @Test
    @DisplayName("Sincrono: 1° num| , luego num2")
    public void concat() {
        Flux<Integer> num1 = Flux.just(1, 3, 5, 7, 9);
        Flux<Integer> num2 = Flux.just(2, 4, 6, 8, 10);
        Flux<Integer> result = Flux.concat(
                num1.delayElements(Duration.ofSeconds(2)),
                num2.delayElements(Duration.ofSeconds(1))
        );
        Iterable<Integer> iterable = result.toIterable();
        iterable.forEach(System.out::println);
    }

    @Test
    @DisplayName("Asincrono: 1° el mas rapido")
    public void merge() {
        Flux<Integer> num1 = Flux.just(1, 3, 5, 7, 9);
        Flux<Integer> num2 = Flux.just(2, 4, 6, 8, 10);
        Flux<Integer> result = Flux.merge(
                num1.delayElements(Duration.ofSeconds(2)),
                num2.delayElements(Duration.ofSeconds(1))
        );
        Iterable<Integer> iterable = result.toIterable();
        iterable.forEach(System.out::println);
    }
}
