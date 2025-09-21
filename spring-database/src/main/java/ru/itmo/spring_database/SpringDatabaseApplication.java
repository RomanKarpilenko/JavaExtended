package ru.itmo.spring_database;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmo.spring_database.dao.CityDao;
import ru.itmo.spring_database.dao.TranslationDao;
import ru.itmo.spring_database.util.State;
import ru.itmo.spring_database.util.StateMachine;

@Slf4j
@SpringBootApplication
public class SpringDatabaseApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringDatabaseApplication.class, args);
        CityDao cityDao = applicationContext.getBean(CityDao.class);
        TranslationDao translationDao = applicationContext.getBean(TranslationDao.class);
        StateMachine stateMachine = applicationContext.getBean(StateMachine.class);

        cityDao.deleteAll();
        translationDao.deleteAll();

        while (stateMachine.getCurrentState() != State.EXIT) {
            stateMachine.start();
        }
        log.info("Справочник завершил работу");
    }
}
