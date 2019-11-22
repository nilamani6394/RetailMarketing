package com.cms.app.main;

import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@SpringBootApplication
//@ComponentScan(basePackages= {"com.cms.app.main.controller"})
public class CmsShopingCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(CmsShopingCartApplication.class, args);
	}

}
