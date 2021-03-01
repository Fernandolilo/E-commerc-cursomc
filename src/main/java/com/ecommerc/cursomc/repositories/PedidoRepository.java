package com.ecommerc.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerc.cursomc.domain.Pedido;

@Repository //faz o tratamento de acesso a dados
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	

}
