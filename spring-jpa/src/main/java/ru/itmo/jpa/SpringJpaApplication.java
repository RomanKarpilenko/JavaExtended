package ru.itmo.jpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmo.jpa.repository.CityRepository;
import ru.itmo.jpa.repository.TranslationKeyRepository;
import ru.itmo.jpa.repository.TranslationRepository;
import ru.itmo.jpa.util.State;
import ru.itmo.jpa.util.StateMachine;

@Slf4j
@SpringBootApplication
public class SpringJpaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringJpaApplication.class, args);
        CityRepository cityRepository = applicationContext.getBean(CityRepository.class);
        TranslationRepository translationRepository = applicationContext.getBean(TranslationRepository.class);
        TranslationKeyRepository translationKeyRepository = applicationContext.getBean(TranslationKeyRepository.class);
        StateMachine stateMachine = applicationContext.getBean(StateMachine.class);

        cityRepository.deleteAll();
        translationRepository.deleteAll();
        translationKeyRepository.deleteAll();

        while (stateMachine.getCurrentState() != State.EXIT) {
            stateMachine.start();
        }
        log.info("Справочник завершил работу");
    }
}
