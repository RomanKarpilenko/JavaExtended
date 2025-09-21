package ru.itmo.spring_database.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itmo.spring_database.dao.CityDao;
import ru.itmo.spring_database.model.City;

import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CityDaoImpl implements CityDao {

    private final NamedParameterJdbcOperations namedParameterJdbcTemplate;

    public Long create(
            Long id,
            String code,
            String nameTranslationKey,
            Long population
    ) {
        namedParameterJdbcTemplate.update("""
                    insert into cities (id,
                                        code,
                                        name_translation_key,
                                        population)
                    values (:id,
                            :code,
                            :nameTranslationKey,
                            :population)
                """, Map.of(
                "id", id,
                "code", code,
                "nameTranslationKey", nameTranslationKey,
                "population", population)
        );
        return id;
    }

    @Override
    public void deleteById(Long id) {
        namedParameterJdbcTemplate.update("""
                delete from cities
                where id = :id
                """, Map.of("id", id));
    }

    @Override
    public Optional<City> findById(Long id) {
        String sql = """
                select id, code, name_translation_key, population
                from cities
                where id = :id
                """;

        try {
            City city = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    Map.of("id", id),
                    (rs, rowNum) -> new City(
                            rs.getLong("id"),
                            rs.getString("code"),
                            rs.getString("name_translation_key"),
                            rs.getLong("population")
                    )
            );
            return Optional.ofNullable(city);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public String description(Long id) {
        String descriptionSql = """
                select id, code as cityCode, name_translation_key as translationKey, population
                from cities
                where id = :id
                """;
        Map<String, Object> descriptionMap;
        try {
            descriptionMap = namedParameterJdbcTemplate.queryForMap(
                    descriptionSql,
                    Map.of("id", id)
            );
        } catch (EmptyResultDataAccessException e) {
            return "Город с id %s не найден".formatted(id.toString());
        }

        String translationSql = """
                select ru, en
                from translations
                where translation_key = :translationKey
                """;

        Map<String, Object> translations = namedParameterJdbcTemplate.queryForMap(
                translationSql,
                Map.of("translationKey", descriptionMap.get("translationKey"))
        );

        String cityId = descriptionMap.get("id").toString();
        String cityCode = descriptionMap.get("cityCode").toString();
        String ru = translations.getOrDefault("ru", "").toString();
        String en = translations.getOrDefault("en", "").toString();
        String population = descriptionMap.get("population").toString();
        return String.format("city_id: %s\ncode: %s\nru: %s\nen: %s\npopulation: %s", cityId, cityCode, ru, en, population);
    }

    @Override
    public void deleteAll() {
        namedParameterJdbcTemplate.getJdbcOperations().execute("""
                truncate table cities
                """);
    }
}
