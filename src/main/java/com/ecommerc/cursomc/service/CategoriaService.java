package com.ecommerc.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ecommerc.cursomc.DTO.CategoriaDTO;
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
	public void delete(Integer id) {
		find (id);
		try {
		repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir uma categoria que possui produtos");
		}
	}
	
	// metodo de paginação para evitar sobrecarga no banco de dados!
	public Page <Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageResquest = PageRequest.of(page, linesPerPage, org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);
		return repo.findAll(pageResquest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDto) {
		return new Categoria(objDto.getId(), objDto.getNome());
	}
	
}
