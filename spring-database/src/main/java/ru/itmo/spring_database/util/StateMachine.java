package ru.itmo.spring_database.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.itmo.spring_database.dao.CityDao;
import ru.itmo.spring_database.dao.TranslationDao;

import java.util.Map;
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

    private final TranslationDao translationDao;
    private final CityDao cityDao;

    private long incId = 0L;
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
                incId++;
                translationDao.create(
                        nameTranslationKey,
                        Map.of("ru", ruTranslation, "en", enTranslation)
                );
                cityDao.create(
                        incId,
                        cityCode,
                        nameTranslationKey,
                        population
                );
                log.info("Данные сохранены!");
                log.info(cityDao.description(incId));
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

