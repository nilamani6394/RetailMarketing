package com.cms.app.main.webconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	/*public void addViewController(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
	*/
	 @Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		 registry.addViewController("/").setViewName("home");
		//WebMvcConfigurer.super.addViewControllers(registry);
	}

}
