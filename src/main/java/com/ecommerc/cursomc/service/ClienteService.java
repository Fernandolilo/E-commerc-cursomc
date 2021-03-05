package com.ecommerc.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ecommerc.cursomc.DTO.ClienteDTO;
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
	
	
		//faz atualização
		public Cliente update(Cliente obj) {
			Cliente newObj = find(obj.getId());
			updateData(newObj, obj);
			return repo.save(newObj);
		}

		//busca uma lista de categoria
		public List<Cliente> findAll() {
			return repo.findAll();
		}
		public void delete(Integer id) {
			find (id);
			try {
			repo.deleteById(id);
			}catch(DataIntegrityViolationException e) {
				throw new DataIntegrityViolationException("Não é possível excluir por que há entidade relacionadas");
			}
		}
		
		// metodo de paginação para evitar sobrecarga no banco de dados!
		public Page <Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
			PageRequest pageResquest = PageRequest.of(page, linesPerPage, org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);
			return repo.findAll(pageResquest);
		}
		
		public Cliente fromDTO(ClienteDTO objDto) {
			return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null, null);
		}
		
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
