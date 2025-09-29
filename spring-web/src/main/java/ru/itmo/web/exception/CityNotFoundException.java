package ru.itmo.web.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CityNotFoundException extends RuntimeException {
    private final String cityId;
}
