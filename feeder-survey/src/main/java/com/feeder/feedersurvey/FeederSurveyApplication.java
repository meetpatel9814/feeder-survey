package com.feeder.feedersurvey;

import com.feeder.feedersurvey.entity.FeederDetail;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import javax.persistence.Entity;

@SpringBootApplication
@EnableJpaRepositories("com.feeder.*")
@ComponentScan(basePackages = {"com.feeder.*"})
@EntityScan(value = "com.feeder.feedersurvey.*")
public class FeederSurveyApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeederSurveyApplication.class, args);
	}

}
