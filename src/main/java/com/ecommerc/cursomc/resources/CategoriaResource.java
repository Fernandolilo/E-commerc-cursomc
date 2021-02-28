package com.ecommerc.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController // Contrele de Rest
@RequestMapping(value="/categorias") //cria o endepoint
public class CategoriaResource {
	

	@RequestMapping(method = RequestMethod.GET) //metodo do request, metodo de busca.
	public String listar() {
		return "rest esta funcionando";
	}
}
