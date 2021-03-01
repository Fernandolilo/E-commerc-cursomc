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

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);		
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		
		categotiaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
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
		
		
	}

}





