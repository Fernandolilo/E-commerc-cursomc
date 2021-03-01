package com.ecommerc.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerc.cursomc.domain.Pedido;
import com.ecommerc.cursomc.service.PedidoService;



@RestController // Contrele de Rest
@RequestMapping(value="/pedidos") //cria o endepoint
public class PedidoResource {
	
	@Autowired
	private PedidoService service;

	@RequestMapping(value= "/{id}", method = RequestMethod.GET) //metodo do request, metodo de busca.
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Pedido obj = service.find(id);
		return ResponseEntity.ok().body(obj);	
	}
	
	
	
}
