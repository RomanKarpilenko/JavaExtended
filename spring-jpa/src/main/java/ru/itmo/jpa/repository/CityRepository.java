package ru.itmo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.jpa.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
