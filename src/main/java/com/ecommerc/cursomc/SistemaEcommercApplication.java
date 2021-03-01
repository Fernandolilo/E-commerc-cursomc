package com.ecommerc.cursomc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecommerc.cursomc.domain.Categoria;
import com.ecommerc.cursomc.domain.Produto;
import com.ecommerc.cursomc.repositories.CategoriaRepository;
import com.ecommerc.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class SistemaEcommercApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SistemaEcommercApplication.class, args);
		
	}
	
	@Autowired
	private CategoriaRepository categotiaRepository;
	
	@Autowired 
	private ProdutoRepository produtoRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categotiaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
	}

}





