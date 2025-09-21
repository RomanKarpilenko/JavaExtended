package ru.itmo.spring_database.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface TranslationDao {
    String create(String translationKey, Map<String, String> translations);
    Optional<String> getTranslation(String locale, String translationKey);
    HashMap<String, String> getTranslations(String locale);
    void deleteAll();
}
