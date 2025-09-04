package ru.itmo.javaadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmo.javaadvanced.dto.ExampleDto;

@SpringBootApplication
public class JavaadvancedApplication {

    public static final ExampleDto EXAMPLE_DTO = new ExampleDto();

	public static void main(String[] args) {
        EXAMPLE_DTO.setId(1L);
        System.out.println(EXAMPLE_DTO.getId());
		SpringApplication.run(JavaadvancedApplication.class, args);
	}

}
