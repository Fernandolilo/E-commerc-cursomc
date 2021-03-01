package com.ecommerc.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerc.cursomc.domain.Categoria;
import com.ecommerc.cursomc.repositories.CategoriaRepository;

@Service //regra de negocio
public class CategoriaService {
	
	@Autowired //automanticamente instaciada pelo spring, 
	//importe da class de tratamento de dados
	private CategoriaRepository repo;
	
	
	// criando o metodo de buscar categotias
	public Optional<Categoria> find(Integer id) {
		
		Optional<Categoria> obj = repo.findById(id);
		
		return obj;
	}
}
