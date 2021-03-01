package com.ecommerc.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecommerc.cursomc.domain.Categoria;
import com.ecommerc.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class SistemaEcommercApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SistemaEcommercApplication.class, args);
		
	}
	
	@Autowired
	private CategoriaRepository categotiaRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		categotiaRepository.saveAll(Arrays.asList(cat1,cat2));
		
	}

}





