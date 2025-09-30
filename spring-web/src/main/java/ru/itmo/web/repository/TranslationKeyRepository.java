package ru.itmo.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.web.model.TranslationKey;

public interface TranslationKeyRepository extends JpaRepository<TranslationKey, Long> {
}
