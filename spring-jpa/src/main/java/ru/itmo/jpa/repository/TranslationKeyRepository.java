package ru.itmo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.jpa.model.TranslationKey;

public interface TranslationKeyRepository extends JpaRepository<TranslationKey, Long> {
}
