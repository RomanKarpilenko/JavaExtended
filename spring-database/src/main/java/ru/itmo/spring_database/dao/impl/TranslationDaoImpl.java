package ru.itmo.spring_database.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itmo.spring_database.dao.TranslationDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class TranslationDaoImpl implements TranslationDao {
    private final NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @Override
    public String create(String translationKey, Map<String, String> translations) {
        String columns = "translation_key, " + String.join(", ", translations.keySet());
        String placeholders = ":translationKey, " + translations.keySet().stream()
                .map(k -> ":" + k)
                .collect(Collectors.joining(", "));

        String sql = "insert into translations (%s) values (%s)".formatted(columns, placeholders);
        Map<String, Object> params = new HashMap<>();
        params.put("translationKey", translationKey);
        params.putAll(translations);
        namedParameterJdbcTemplate.update(sql, params);

        return translationKey;
    }

    @Override
    public Optional<String> getTranslation(String locale, String translationKey) {

        String sql = """
                select %s
                from translations
                where translation_key = :translationKey
                """.formatted(locale);

        try {
            String result = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    Map.of("translationKey", translationKey),
                    String.class
            );
            return Optional.ofNullable(result);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public HashMap<String, String> getTranslations(String locale) {
        String sql = """
                select translation_key, %s
                from translations
                """.formatted(locale);

        return namedParameterJdbcTemplate.query(
                sql,
                Map.of(),
                rs -> {
                    HashMap<String, String> map = new HashMap<>();
                    while (rs.next()) {
                        String key = rs.getString("translation_key");
                        String value = rs.getString(locale);
                        map.put(key, value);
                    }
                    return map;
                }
        );
    }

    @Override
    public void deleteAll() {
        namedParameterJdbcTemplate.getJdbcOperations().execute("""
                truncate table translations
                """);
    }
}
