package nexadigital.finexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinexaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinexaApplication.class, args);
		System.out.println("Hello System JAVA!");
		System.out.print("SPRING BOOT");
	}
}
