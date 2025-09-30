package ru.itmo.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.web.model.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
