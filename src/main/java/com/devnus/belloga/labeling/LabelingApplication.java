package com.devnus.belloga.labeling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LabelingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabelingApplication.class, args);
	}

}
