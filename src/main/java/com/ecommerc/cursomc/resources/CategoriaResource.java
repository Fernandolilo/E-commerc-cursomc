package com.ecommerc.cursomc.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerc.cursomc.domain.Categoria;
import com.ecommerc.cursomc.service.CategoriaService;



@RestController // Contrele de Rest
@RequestMapping(value="/categorias") //cria o endepoint
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@RequestMapping(value= "/{id}", method = RequestMethod.GET) //metodo do request, metodo de busca.
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Optional<Categoria> obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
		
	}
}
