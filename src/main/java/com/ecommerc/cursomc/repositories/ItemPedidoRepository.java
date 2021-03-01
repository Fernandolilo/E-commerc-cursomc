package com.ecommerc.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerc.cursomc.domain.ItemPedido;

@Repository //faz o tratamento de acesso a dados
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
	

}
