package com.ecommerc.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ecommerc.cursomc.domain.Categoria;
import com.ecommerc.cursomc.domain.Cidade;
import com.ecommerc.cursomc.domain.Cliente;
import com.ecommerc.cursomc.domain.Endereco;
import com.ecommerc.cursomc.domain.Estado;
import com.ecommerc.cursomc.domain.ItemPedido;
import com.ecommerc.cursomc.domain.Pagamento;
import com.ecommerc.cursomc.domain.PagamentoComBoleto;
import com.ecommerc.cursomc.domain.PagamentoComCartao;
import com.ecommerc.cursomc.domain.Pedido;
import com.ecommerc.cursomc.domain.Produto;
import com.ecommerc.cursomc.domain.enums.EstadoPagamento;
import com.ecommerc.cursomc.domain.enums.TipoCliente;
import com.ecommerc.cursomc.repositories.CategoriaRepository;
import com.ecommerc.cursomc.repositories.CidadeRepository;
import com.ecommerc.cursomc.repositories.ClienteRepository;
import com.ecommerc.cursomc.repositories.EnderecoRepository;
import com.ecommerc.cursomc.repositories.EstadoRepository;
import com.ecommerc.cursomc.repositories.ItemPedidoRepository;
import com.ecommerc.cursomc.repositories.PagamentoRepository;
import com.ecommerc.cursomc.repositories.PedidoRepository;
import com.ecommerc.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class SistemaEcommercApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SistemaEcommercApplication.class, args);
		
	}
	
	@Autowired
	private CategoriaRepository categotiaRepository;
	
	@Autowired 
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		Categoria cat3 = new Categoria(null, "Cama Mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletronicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
			
		
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);	
		Produto p4 = new Produto(null, "Mesa de Escritorio", 300.00);	
		Produto p5 = new Produto(null, "Toalha", 50.00);	
		Produto p6 = new Produto(null, "Colcha", 200.00);	
		Produto p7 = new Produto(null, "TV true color", 1200.00);	
		Produto p8 = new Produto(null, "Roçadeira", 800.00);	
		Produto p9 = new Produto(null, "Abajour", 100.00);	
		Produto p10 = new Produto(null, "Pendente", 180.00);	
		Produto p11 = new Produto(null, "Shampoo", 90.00);	
		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2, p4));
		cat3.getProdutos().addAll(Arrays.asList(p5, p6));
		cat4.getProdutos().addAll(Arrays.asList(p1,p2, p3,p4, p7));
		cat5.getProdutos().addAll(Arrays.asList(p8));
		cat6.getProdutos().addAll(Arrays.asList(p9, p10));
		cat7.getProdutos().addAll(Arrays.asList(p11));
		
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));
		
		categotiaRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6,p7, p8, p9, p10, p11));
		
		Estado est1 = new Estado(null, "São Paulo");
		Estado est2 = new Estado(null, "Minas Gerais");
		
		Cidade c1 = new Cidade(null, "São Paulo",est1);
		Cidade c2 = new Cidade(null, "Campinas", est1);
		Cidade c3 = new Cidade(null, "Uberlandia", est2);
						
		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c3));
				
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente (null, "Maria Silva", "maria@gmail.com", "12312312311",TipoCliente.PESSOAFISICA, null, null);		
		cli1.getTelefones().addAll(Arrays.asList("2725-1000", "98585-1234"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "apto 13", "Jardim Boa vista", "12311-000", cli1, c1);
		Endereco e2 = new Endereco (null, "Rua felisberto", "150", "a", "jadir Albertina", "50000-000", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));		
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("01/03/2021 03:11"), cli1, e1);
		Pedido ped2 =  new Pedido(null, sdf.parse("01/03/2021 03:17"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao( null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 =  new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("01/03/2021 03:23"),null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1 , ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));		
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 1, 0.00, 2000.00);
		ItemPedido ip2 =  new ItemPedido(ped2, p3, 2, 0.00, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 1, 100.00, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}





