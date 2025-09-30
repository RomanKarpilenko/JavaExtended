package ru.itmo.web.mapper;

import org.mapstruct.Mapper;
import ru.itmo.web.dto.TranslationKeyDto;
import ru.itmo.web.model.TranslationKey;

@Mapper(uses = TranslationMapper.class)
public interface TranslationKeyMapper {
    TranslationKeyDto toDto(TranslationKey entity);
    TranslationKey toEntity(TranslationKeyDto dto);
}
