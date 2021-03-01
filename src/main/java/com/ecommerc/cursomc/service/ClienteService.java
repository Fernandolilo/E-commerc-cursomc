package com.ecommerc.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerc.cursomc.domain.Cliente;
import com.ecommerc.cursomc.repositories.ClienteRepository;
import com.ecommerc.cursomc.service.exceptions.ObjectNotFoundException;

@Service //regra de negocio
public class ClienteService {
	
	@Autowired //automanticamente instaciada pelo spring, 
	//importe da class de tratamento de dados
	private ClienteRepository repo;
	
	
	public Cliente find(Integer id) {
		 Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
}
