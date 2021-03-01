package com.ecommerc.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerc.cursomc.domain.Estado;

@Repository //faz o tratamento de acesso a dados
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	

}
