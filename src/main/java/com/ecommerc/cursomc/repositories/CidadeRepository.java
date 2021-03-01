package com.ecommerc.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerc.cursomc.domain.Cidade;

@Repository //faz o tratamento de acesso a dados
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
	

}
