package ru.itmo.spring_database.dao;

import java.util.Map;
import java.util.Optional;

public interface TranslationDao {
    String create(String translationKey, Map<String, String> translations);
    Optional<String> getTranslation(String locale, String translationKey);
    Map<String, String> getAllTranslationsByLocale(String locale);
    Map<String, String> getTranslationsByKey(String translationKey);
    void deleteAll();
}
