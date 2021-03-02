package com.ecommerc.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerc.cursomc.domain.Categoria;
import com.ecommerc.cursomc.repositories.CategoriaRepository;
import com.ecommerc.cursomc.service.exceptions.ObjectNotFoundException;

@Service // regra de negocio
public class CategoriaService {

	@Autowired // automanticamente instaciada pelo spring,
	// importe da class de tratamento de dados
	private CategoriaRepository repo;

	//busca um ID
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	//faz a inserção
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	//faz atualização
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	//busca uma lista de categoria
	public List<Categoria> findAll() {
		return repo.findAll();
	}
}
