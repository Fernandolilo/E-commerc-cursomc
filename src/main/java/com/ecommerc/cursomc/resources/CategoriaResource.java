package com.ecommerc.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerc.cursomc.domain.Categoria;

@RestController // Contrele de Rest
@RequestMapping(value="/categorias") //cria o endepoint
public class CategoriaResource {
	

	@RequestMapping(method = RequestMethod.GET) //metodo do request, metodo de busca.
	public List<Categoria> categorias() {
		
		Categoria cat1 = new Categoria(1, "Informatica");
		Categoria cat2 = new Categoria(2, "Escritorio");
		
		List<Categoria> categorias = new ArrayList<>();
		categorias.add(cat1);
		categorias.add(cat2);
		return categorias;
		
		
	}
}
