package com.ecommerc.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerc.cursomc.DTO.CategoriaDTO;
import com.ecommerc.cursomc.domain.Categoria;
import com.ecommerc.cursomc.service.CategoriaService;

@RestController // Contrele de Rest
@RequestMapping(value = "/categorias") // cria o endepoint
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	// get == buscar por id
	@RequestMapping(value = "/{id}", method = RequestMethod.GET) // metodo do request, metodo de busca.
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}

	// insert
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj =service.fromDTO(objDto);
		obj = service.insert(obj);
		// metodo para disponibilizar a URI do insert no body do Http
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// update
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		Categoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);

		return ResponseEntity.noContent().build();
	}

	// get == buscar por id
	@RequestMapping(method = RequestMethod.GET) // metodo do request, metodo de busca.
		public ResponseEntity <List<CategoriaDTO>> findAll() {
			 List<Categoria> list = service.findAll();
			 List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDTO);
		}
	
	// get == buscar por id
		@RequestMapping(value= "/page",  method = RequestMethod.GET) // metodo do request, metodo de busca.
			public ResponseEntity <Page<CategoriaDTO>> findPage(
					@RequestParam(value = "page", defaultValue = "0") Integer page, 
					@RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage, 
					@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
					@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
				 Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
				 Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
				return ResponseEntity.ok().body(listDTO);
			}

}
