package github.denisspec989.azsmainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class AzsMainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AzsMainServiceApplication.class, args);
	}

}
