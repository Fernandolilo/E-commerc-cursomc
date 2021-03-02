package com.ecommerc.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerc.cursomc.domain.Categoria;
import com.ecommerc.cursomc.repositories.CategoriaRepository;
import com.ecommerc.cursomc.service.exceptions.ObjectNotFoundException;

@Service //regra de negocio
public class CategoriaService {
	
	@Autowired //automanticamente instaciada pelo spring, 
	//importe da class de tratamento de dados
	private CategoriaRepository repo;
	
	
	public Categoria find(Integer id) {
		 Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
}
