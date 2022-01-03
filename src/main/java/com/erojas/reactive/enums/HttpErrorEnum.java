package com.erojas.reactive.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author : erojas
 * @Datecreation : 26/11/2021 21:36
 * @FileName : HttpErrorEnum.java
 * @Description : Enumeration http errror
 */
@Getter
@AllArgsConstructor
public enum HttpErrorEnum {

    NOT_FOUND_ARTIST("ARTIST: Not found artist information by artistId: %s"),
    NOT_FOUND_PERSON("PERSON: Not found person information by artistId: %s"),
    NOT_FOUND_ALBUM("ALBUM: Not found album information by artistId: %s"),
    NOT_FOUND_TRACK("TRACK: Not found track information by artistId: %s"),

    BAD_REQUEST_ARTIST("ARTIST: Invalid value by field artistId: %s"),
    BAD_REQUEST_PERSON("PERSON: Invalid value by field artistId: %s"),
    BAD_REQUEST_ALBUM("ALBUM: Invalid value by field artistId: %s"),
    BAD_REQUEST_TRACK("TRACK: Invalid value by field artistId: %s"),

    INTERNAL_ERROR_ARTIST("Internal error in artist : %s"),
    INTERNAL_ERROR_ARTIST_DUPLICATE("Internal error in artist : Duplicate Key by artistId = %s"),
    INTERNAL_ERROR_PERSON("Internal error in person : %s"),
    INTERNAL_ERROR_PERSON_DUPLICATE("Internal error in person : Duplicate Key by personId = %s"),
    INTERNAL_ERROR_ALBUM("Internal error in album : %s"),
    INTERNAL_ERROR_ALBUM_DUPLICATE("Internal error in album : Duplicate Key by albumId = %s"),
    INTERNAL_ERROR_TRACK("Internal error in track : %s"),
    INTERNAL_ERROR_TRACK_DUPLICATE("Internal error in track : Duplicate Key by trackId = %s");

    private String message;
}
