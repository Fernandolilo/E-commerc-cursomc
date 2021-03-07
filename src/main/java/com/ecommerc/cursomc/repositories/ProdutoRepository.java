package com.ecommerc.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerc.cursomc.domain.Categoria;
import com.ecommerc.cursomc.domain.Produto;

@Repository //faz o tratamento de acesso a dados
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	//@Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	//Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria>categorias,Pageable pageResquest);
	
	@Transactional(readOnly = true)
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn( String nome, List<Categoria>categorias,Pageable pageResquest);

	
}
