package ru.itmo.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.web.model.Translation;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

}
