package ru.itmo.spring_database.dao;

import ru.itmo.spring_database.model.City;

import java.util.Optional;

public interface CityDao {
    Long create(
            Long id,
            String code,
            String nameTranslationKey,
            Long population
    );

    void deleteById(Long id);

    Optional<City> findById(Long id);

    String description(Long id);

    void deleteAll();
}
