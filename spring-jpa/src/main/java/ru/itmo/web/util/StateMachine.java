package ru.itmo.web.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.itmo.web.model.City;
import ru.itmo.web.model.LanguageCode;
import ru.itmo.web.model.Translation;
import ru.itmo.web.model.TranslationKey;
import ru.itmo.web.repository.CityRepository;
import ru.itmo.web.repository.TranslationKeyRepository;
import ru.itmo.web.repository.TranslationRepository;
import ru.itmo.web.service.CityService;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Slf4j
@Component
@RequiredArgsConstructor
public class StateMachine {
    private final State initialState = State.WAIT_CODE;
    private final Scanner scanner = new Scanner(System.in);

    @Getter
    private State currentState = initialState;

    private final TranslationRepository translationRepository;
    private final TranslationKeyRepository translationKeyRepository;
    private final CityRepository cityRepository;
    private final CityService cityService;

    private String cityCode;
    private String nameTranslationKey;
    private String ruTranslation;
    private String enTranslation;
    private Long population;

    public void start() {
        switch (currentState) {
            case WAIT_CODE -> {
                log.info("Введите код города:");
                cityCode = scanner.nextLine();
                currentState = State.WAIT_NAME_TR_KEY;
            }
            case WAIT_NAME_TR_KEY -> {
                log.info("Введите ключ перевода для имени города:");
                nameTranslationKey = scanner.nextLine();
                currentState = State.WAIT_RU_TR;
            }
            case WAIT_RU_TR -> {
                log.info("Введите перевод на русский язык для {}:", nameTranslationKey);
                ruTranslation = scanner.nextLine();
                currentState = State.WAIT_EN_TR;
            }
            case WAIT_EN_TR -> {
                log.info("Введите перевод на английский язык для {}:", nameTranslationKey);
                enTranslation = scanner.nextLine();
                currentState = State.WAIT_POPULATION;
            }
            case WAIT_POPULATION -> {
                log.info("Введите численность города:");
                population = Long.valueOf(scanner.nextLine());
                currentState = State.DONE;
            }
            case DONE -> {
                TranslationKey nameTranslationKey = translationKeyRepository.save(
                        new TranslationKey(
                                this.nameTranslationKey
                        )
                );
                translationRepository.saveAll(
                        List.of(new Translation(
                                nameTranslationKey,
                                LanguageCode.RU.toString(),
                                this.ruTranslation
                        ), new Translation(
                                nameTranslationKey,
                                LanguageCode.EN.toString(),
                                this.enTranslation
                        ))
                );
                City city = cityRepository.save(
                        new City(
                                nameTranslationKey,
                                this.cityCode,
                                this.population
                        )
                );
                log.info("Данные сохранены!");
                log.info(cityService.description(city.getId()));
                currentState = State.CONTINUE;
            }
            case CONTINUE -> {
                log.info("Вы хотите продолжить y/n?");
                String result = scanner.nextLine();
                if (Objects.equals(result, "y")) {
                    currentState = State.WAIT_CODE;
                } else {
                    currentState = State.EXIT;
                }
            }
            case EXIT -> {
                log.info("Пользователь завершил работу");
            }
        }
    }
}

