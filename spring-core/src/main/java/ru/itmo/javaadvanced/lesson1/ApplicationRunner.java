package ru.itmo.javaadvanced.lesson1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmo.javaadvanced.lesson1.dto.ExampleDto;

@SpringBootApplication
public class ApplicationRunner {

    public static final ExampleDto EXAMPLE_DTO = new ExampleDto();

	public static void main(String[] args) {
        EXAMPLE_DTO.setId(1L);
        System.out.println(EXAMPLE_DTO.getId());
		SpringApplication.run(ApplicationRunner.class, args);
	}

}
