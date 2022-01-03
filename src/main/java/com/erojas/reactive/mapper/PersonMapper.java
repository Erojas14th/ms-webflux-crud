package com.erojas.reactive.mapper;

import com.erojas.reactive.entity.PersonEntity;
import com.erojas.reactive.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @Author : erojas
 * @Datecreation : 30/12/2021 23:26
 * @FileName : PersonMapper.java
 * @Description : Mapper person
 */
@Mapper
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "version", ignore = true)
    PersonEntity apiToEntity(Person api);

    Person entityToApi(PersonEntity api);

    List<PersonEntity> apiToEntity(List<Person> api);

    List<Person> entityToApi(List<PersonEntity> api);
}
