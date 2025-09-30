package ru.itmo.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itmo.web.dto.CityDto;
import ru.itmo.web.dto.DescriptionDto;
import ru.itmo.web.service.CityService;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cities")
public class CityController {
    private final CityService cityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCity(
            @RequestBody
            @Valid
            CityDto cityDto
    ) {
        cityService.createCity(cityDto);
    }

    @PutMapping("/{id}")
    public void updateCity(
            @PathVariable
            Long id,
            @RequestBody
            @Valid
            CityDto cityDto
    ) {
        cityService.updateCity(id, cityDto);
    }

    @GetMapping("/{id}")
    public Optional<CityDto> findById(@PathVariable Long id) {
        return cityService.findById(id);
    }

    @GetMapping
    public List<CityDto> findAll() {
        return cityService.findAll();
    }

    @GetMapping("description/{id}")
    public DescriptionDto description(@PathVariable Long id) {
        return cityService.description(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCity(@PathVariable Long id) {
        cityService.deleteCity(id);
    }
}
