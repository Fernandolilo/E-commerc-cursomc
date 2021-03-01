package com.ecommerc.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerc.cursomc.domain.Pedido;
import com.ecommerc.cursomc.repositories.PedidoRepository;
import com.ecommerc.cursomc.service.exceptions.ObjectNotFoundException;

@Service //regra de negocio
public class PedidoService {
	
	@Autowired //automanticamente instaciada pelo spring, 
	//importe da class de tratamento de dados
	private PedidoRepository repo;
	
	
	public Pedido find(Integer id) {
		 Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
}
