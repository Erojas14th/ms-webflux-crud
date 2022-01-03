package com.erojas.reactive.controller;

import com.erojas.reactive.model.Artist;
import com.erojas.reactive.service.ProductIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 22:00
 * @FileName : ArtistController.java
 * @Description : API artist
 */
@RestController
public class ArtistController {

    @Autowired
    private ProductIntegrationService productIntegrationService;


    @GetMapping(value = "/artist", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Artist> get(@RequestParam Integer artistId) {
        return productIntegrationService.get(artistId);
    }


    @PostMapping(value = "/artist", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Artist> save(@RequestBody Artist artist) {
        return productIntegrationService.save(artist);
    }

    @DeleteMapping(value = "/artist")
    public Mono<Void> delete(@RequestParam Integer artistId) {
        return productIntegrationService.delete(artistId);
    }
}
