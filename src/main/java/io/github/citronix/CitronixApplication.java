package io.github.citronix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "io.github.citronix" })
public class CitronixApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitronixApplication.class, args);
	}

}
