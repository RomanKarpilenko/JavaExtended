package ru.itmo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.jpa.model.Translation;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

}
