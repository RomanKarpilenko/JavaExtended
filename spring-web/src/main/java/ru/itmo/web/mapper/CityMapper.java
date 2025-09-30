package ru.itmo.web.mapper;

import org.mapstruct.Mapper;
import ru.itmo.web.dto.CityDto;
import ru.itmo.web.model.City;

import java.util.List;

@Mapper(uses = TranslationMapper.class)
public interface CityMapper {
    CityDto toDto(City city);
    List<CityDto> toDto(List<City> city);

    City toEntity(CityDto dto);
}
