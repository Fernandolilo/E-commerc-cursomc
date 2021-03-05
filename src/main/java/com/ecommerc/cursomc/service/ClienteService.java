package com.ecommerc.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ecommerc.cursomc.DTO.ClienteDTO;
import com.ecommerc.cursomc.DTO.ClienteNewDTO;
import com.ecommerc.cursomc.domain.Cidade;
import com.ecommerc.cursomc.domain.Cliente;
import com.ecommerc.cursomc.domain.Endereco;
import com.ecommerc.cursomc.domain.enums.TipoCliente;
import com.ecommerc.cursomc.repositories.ClienteRepository;
import com.ecommerc.cursomc.repositories.EnderecoRepository;
import com.ecommerc.cursomc.service.exceptions.ObjectNotFoundException;

@Service // regra de negocio
public class ClienteService {

	@Autowired // automanticamente instaciada pelo spring,
	// importe da class de tratamento de dados
	private ClienteRepository repo;
	
	@Autowired
	EnderecoRepository enderecoRepository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
	}

	// faz a inserção
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		obj =repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	// faz atualização
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	// busca uma lista de categoria
	public List<Cliente> findAll() {
		return repo.findAll();
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possível excluir por que há pedidos relacionadas");
		}
	}

	// metodo de paginação para evitar sobrecarga no banco de dados!
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageResquest = PageRequest.of(page, linesPerPage,
				org.springframework.data.domain.Sort.Direction.valueOf(direction), orderBy);
		return repo.findAll(pageResquest);
	}

	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDto) {
		Cliente cli = new Cliente(null, objDto.getNome(), objDto.getEmail(), objDto.getCpfOuCnpj(), TipoCliente.toEnum(objDto.getTipo()), null, null);
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDto.getTelefone1());
		if(objDto.getTelefone2() != null) {
			cli.getTelefones().add(objDto.getTelefone2());
		}
		if(objDto.getTelefone3() != null) {
			cli.getTelefones().add(objDto.getTelefone3());
		}
		return cli;
	
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
