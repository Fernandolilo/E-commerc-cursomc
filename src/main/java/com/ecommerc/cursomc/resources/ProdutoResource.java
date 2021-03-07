package com.ecommerc.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerc.cursomc.DTO.CategoriaDTO;
import com.ecommerc.cursomc.DTO.ProdutoDTO;
import com.ecommerc.cursomc.domain.Produto;
import com.ecommerc.cursomc.resources.utils.URL;
import com.ecommerc.cursomc.service.ProdutoService;

@RestController // Contrele de Rest
@RequestMapping(value = "/produtos") // cria o endepoint
public class ProdutoResource {

	@Autowired
	private ProdutoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // metodo do request, metodo de busca.
	public ResponseEntity<Produto> find(@PathVariable Integer id) {

		Produto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// get == buscar por id
	@RequestMapping(method = RequestMethod.GET) // metodo do request, metodo de busca.
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		String nomeDecoder = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Produto> list = service.search(nomeDecoder,ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);
	}

}
