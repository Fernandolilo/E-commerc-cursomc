package com.ecommerc.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ecommerc.cursomc.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private DBService dbService;
	
	
	@Bean
	public boolean instantianteDataBase() throws ParseException {
		dbService.instatiateTestDatabase();
		return true;
	}

}
