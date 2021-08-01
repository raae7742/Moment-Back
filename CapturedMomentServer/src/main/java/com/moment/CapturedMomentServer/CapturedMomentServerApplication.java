package com.moment.CapturedMomentServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CapturedMomentServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapturedMomentServerApplication.class, args);
	}

}
