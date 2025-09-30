package ru.itmo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmo.web.util.State;
import ru.itmo.web.util.StateMachine;

@Slf4j
@SpringBootApplication
public class SpringJpaApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringJpaApplication.class, args);
        StateMachine stateMachine = applicationContext.getBean(StateMachine.class);

        while (stateMachine.getCurrentState() != State.EXIT) {
            stateMachine.start();
        }
        log.info("Справочник завершил работу");
    }
}
