package com.ecommerc.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ecommerc.cursomc.domain.Categoria;
import com.ecommerc.cursomc.domain.Produto;
import com.ecommerc.cursomc.repositories.CategoriaRepository;
import com.ecommerc.cursomc.repositories.ProdutoRepository;
import com.ecommerc.cursomc.service.exceptions.ObjectNotFoundException;

@Service //regra de negocio
public class ProdutoService {
	
	@Autowired //automanticamente instaciada pelo spring, 
	//importe da class de tratamento de dados
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	
	public Produto find(Integer id) {
		 Optional<Produto> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
		}
	
	public Page<Produto>search(String nome, List<Integer>ids,Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageResquest = PageRequest.of(page, linesPerPage, org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageResquest);	
	}
}
