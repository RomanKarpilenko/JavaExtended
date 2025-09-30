package ru.itmo.web.mapper;

import org.mapstruct.Mapper;
import ru.itmo.web.model.Translation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface TranslationMapper {
    default Map<String, String> toMap(List<Translation> translations) {
        if (translations == null) {
            return null;
        }
        return translations.stream()
                .collect(Collectors.toMap(Translation::getLanguageCode, Translation::getValue));
    }

    default List<Translation> toEntities(Map<String, String> translations) {
        if (translations == null) {
            return null;
        }
        return translations.entrySet().stream()
                .map(e -> new Translation(null, e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }
}
