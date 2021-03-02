package com.ecommerc.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerc.cursomc.domain.Cliente;
import com.ecommerc.cursomc.service.ClienteService;



@RestController // Contrele de Rest
@RequestMapping(value="/clientes") //cria o endepoint
public class ClienteResource {
	
	@Autowired
	private ClienteService service;

	@RequestMapping(value= "/{id}", method = RequestMethod.GET) //metodo do request, metodo de busca.
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	
	
}
