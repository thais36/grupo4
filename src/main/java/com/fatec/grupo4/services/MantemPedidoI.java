package com.fatec.grupo4.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fatec.grupo4.model.ItemDePedido;
//import com.fatec.grupo4.model.ItemDePedidoRepository;
import com.fatec.grupo4.model.Pedido;
import com.fatec.grupo4.model.PedidoDTO;
import com.fatec.grupo4.model.PedidoRepository;
import com.fatec.grupo4.model.Produto;
import com.fatec.grupo4.model.ProdutoRepository;

@Service
public class MantemPedidoI implements MantemPedido {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	//@Autowired
	//private ItemDePedidoRepository itemRepository;
	@Autowired
	private MantemAluno mantemAluno;
	private MantemAtleta mantemAtleta;

	public Optional<Pedido> buscaPorId(Long id) {
		return pedidoRepository.findById(id);
	}

	@Override
	public List<Pedido> buscaPorCpf(String cpf) {
		return pedidoRepository.findByCpf(cpf);
	}

	@Transactional
	public Pedido save(Pedido pedido) {
		return pedidoRepository.save(pedido);
		}
	//}
		//logger.info(">>>>>> servico save iniciado ");
		//logger.info(pedido.toString());
		//Pedido pedido1 = pedidoRepository.save(pedido1);
		//logger.info(">>>>>> cabecalho do pedido salvo no repositorio ");
		//for (ItemDePedido item : pedido1.getItens()) {
			//item.setProduto(produtoRepository.findById(item.getProduto().getProdutoId()).get());
			//item.setQuantidade(item.getQuantidade());
	//	}
		//logger.info(pedido1.getItens().get(0).getProduto().toString());
		//itemRepository.saveAll(pedido1.getItens());
		//logger.info(">>>>>> item do pedido salvo no repositorio ");
		//return pedido1;
	//}

	public boolean validaData(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false); //
		try {
			df.parse(data); // data válida (exemplo 30 fev - 31 nov)
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	//@Override
	//public List<Pedido> consultaTodos() {
		//return PedidoRepository.findAll();
	//}
	public void excluiPedido(Long id) {
		pedidoRepository.deleteById(id);
	}
	
	/**
	 * se a entrada de dados para o objeto pedido for valida chama o metodo save
	 * @param pedidoDTO
	 * @return pedido ou null
	 */
	@Transactional
	@Override
	public Pedido cadastrarPedido(PedidoDTO pedidoDTO) {
		logger.info(">>>>>> 2. servico cadastrar pedido iniciado ");
		try {
			Optional<Pedido> pedido = obtemPedido(pedidoDTO);
			if (pedido.isPresent()) {
				logger.info(">>>>>> servico cadastrar pedido - dados validos ");
				return save(pedido.get());
			} else {
				logger.info(">>>>>> servico cadastrar pedido - dados invalidos ");
				return null;
			}
		} catch (Exception e) {
			logger.info(">>>>>> servico cadastrar pedido - erro nao esperado contate o administrador ");
			logger.info(">>>>>> servico cadastrar pedido - erro nao esperado => " + e.getMessage());
			return null;
		}
	}

	
	/**
	 * efetiva as validações na entrada do usuario retorna pedido vazio se a entrada
	 * for invalida.
	 * 
	 * @param pedidoDTO
	 * @return pedido
	 */
	public Optional<Pedido> obtemPedido(PedidoDTO pedidoDTO) {
		// *************************************************************
		// Estrutura de dados do metodo
		// *************************************************************
		Pedido pedido = new Pedido();
		ItemDePedido item ;
		Produto produto = new Produto();
		// *************************************************************
		// Valida a entrada de dados
		// *************************************************************
		if ((consultaPorCpf(pedidoDTO.getCpf()) == true) && (produtoCadastrado(Long.parseLong(pedidoDTO.getProdutoId())) == true)) {
			logger.info(">>>>>> servico obtem pedido - dados validos ");
			DateTime dataAtual = new DateTime();
			DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
			pedido.setDataEmissao(dataAtual.toString(fmt));
			pedido.setCpf(pedidoDTO.getCpf());
			
			Optional<Produto> umProduto = produtoRepository.findById(Long.parseLong(pedidoDTO.getProdutoId()));
			produto = umProduto.get();
							
			logger.info(">>>>>> servico obtem pedido - id do produto =" + produto.getProdutoId());
			item = new ItemDePedido(produto, Integer.parseInt(pedidoDTO.getQuantidade()));
			pedido.getItens().addAll(Arrays.asList(item));
			
		}

		return Optional.ofNullable(pedido);
	}
	/**
	 * verifica se o cpf do cliente esta cadastrado na base
	 * @param cpf
	 * @return true or false
	 */
	public boolean consultaPorCpf(String cpf) {
		return mantemAluno.consultaPorCpf(cpf).isPresent();
	}
	public boolean consultaPorCpf1(String cpf) {
		return mantemAtleta.consultaPorCpf(cpf).isPresent();
	}
	/**
	 * verifica se o id de produto esta cadastrado na base
	 * @param cod - codigo do produto
	 * @return true  or false
	 */
	public boolean produtoCadastrado(Long cod) {
		return produtoRepository.findById(cod).isPresent();
	}

	@Override
	public List<Pedido> consultaTodos() {
		// TODO Auto-generated method stub
		return null;
	}
}
