package com.ram.demo;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@SpringBootApplication
@ComponentScan(value="com.ram.demo.*")
@EnableMongoRepositories(value="com.ram.demo.repository")
public class SampleSbAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleSbAppApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		/* SessionLocaleResolver localeResolver = new SessionLocaleResolver(); */
		AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		return localeResolver;
	}

	/*
	 * Replaced by property in application.properties file
	 * public ResourceBundleMessageSource bundleMessageSource() {
	 * ResourceBundleMessageSource messageSource = new
	 * ResourceBundleMessageSource(); messageSource.addBasenames("messages"); return
	 * messageSource; }
	 */
}

