package com.ecommerc.cursomc.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerc.cursomc.domain.ItemPedido;
import com.ecommerc.cursomc.domain.PagamentoComBoleto;
import com.ecommerc.cursomc.domain.Pedido;
import com.ecommerc.cursomc.domain.enums.EstadoPagamento;
import com.ecommerc.cursomc.repositories.ItemPedidoRepository;
import com.ecommerc.cursomc.repositories.PagamentoRepository;
import com.ecommerc.cursomc.repositories.PedidoRepository;
import com.ecommerc.cursomc.repositories.ProdutoRepository;
import com.ecommerc.cursomc.service.exceptions.ObjectNotFoundException;

@Service //regra de negocio
public class PedidoService {
	
	@Autowired //automanticamente instaciada pelo spring, 
	//importe da class de tratamento de dados
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private	ProdutoRepository produtoReporitory;
	
	@Autowired
	ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		 Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		 "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
		}
	//faz a inserção
		public Pedido insert(Pedido obj) {
			obj.setId(null);
			obj.setInstante(new Date());
			obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
			obj.getPagamento().setPedido(obj);
			if(obj.getPagamento() instanceof PagamentoComBoleto) {
				PagamentoComBoleto pagto =(PagamentoComBoleto) obj.getPagamento();
				boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
			}
			obj = repo.save(obj);
			pagamentoRepository.save(obj.getPagamento());
			
			for (ItemPedido ip: obj.getItens()) {
				ip.setDesconto(0.0);
				ip.setPreco(produtoReporitory.findById(ip.getProduto().getId()).get().getPreco());
				ip.setPedido(obj);
			}
			itemPedidoRepository.saveAll(obj.getItens());
			return obj;
		}	
}
