package com.fatec.grupox.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fatec.grupox.model.ItemDePedido;
import com.fatec.grupox.model.ItemDePedidoRepository;
import com.fatec.grupox.model.Pedido;
import com.fatec.grupox.model.PedidoDTO;
import com.fatec.grupox.model.PedidoRepository;
import com.fatec.grupox.model.ProdutoRepository;

@SuppressWarnings("unused")
@Service
public class MantemPedidoI implements MantemPedido {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ItemDePedidoRepository itemRepository;
	@Autowired
	private MantemAluno mantemAluno;

	public Optional<Pedido> buscaPorId(Long id) {
		return pedidoRepository.findById(id);
	}

	@Override
	public List<Pedido> buscaPorCpf(String cpf) {
		return pedidoRepository.findByCpf(cpf);
	}

	@SuppressWarnings("deprecation")
	@Transactional
	public Pedido save(Pedido pedido) {
		logger.info(">>>>>> servico save chamado para o pedido - " + pedido.getCpf());
		DateTime dataAtual = new DateTime();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		pedido.setDataEmissao(dataAtual.toString(fmt));
		Pedido umPedido = pedidoRepository.save(pedido);
		logger.info(">>>>>> cabecalho do pedido salvo no repositorio ");
		for (ItemDePedido item : pedido.getItens()) {
			item.setProduto(produtoRepository.getById(item.getProduto().getProdutoId()));
			item.setQuantidade(item.getQuantidade());
		}
		itemRepository.saveAll(pedido.getItens());
		logger.info(">>>>>> item do pedido salvo no repositorio ");
		return umPedido;
	}

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

	@Override
	public List<Pedido> consultaTodos() {
		return pedidoRepository.findAll();
	}

	@Transactional
	public void excluiPedido(Long id) {
		pedidoRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Pedido cadastrarPedido(Pedido pedido) {
		logger.info(">>>>>> servico cadastrar pedido ");
		try {
			if (isAlunoCadastrado(pedido.getCpf())) {
				logger.info(">>>>>> servico cadastrar pedido - aluno cadastrado ");
				return save(pedido);
			} else {
				logger.info(">>>>>> servico cadastrar pedido - aluno invalido ");
				return null;
			}
		} catch (Exception e) {
			logger.info(">>>>>> servico cadastrar pedido - erro nao esperado contate o administrador");
			return null;
		}
	}

	/**
	 * Verifica se o cpf esta cadastrado na base retorna true se estiver cadastrado
	 */
	public boolean isAlunoeCadastrado(String cpf) {
		return mantemAluno.consultaPorCpf(cpf).isPresent();
	}

	@Override
	public Pedido cadastrarPedido(PedidoDTO pedidoDTO) {
		return null;
	}

	@Override
	public boolean consultaPorCpf(String cpf) {
		return false;
	}

	@Override
	public boolean isAlunoCadastrado(String cpf) {
		return false;
	}

	@Override
	public Optional<Pedido> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atualiza(Pedido pedido) {
		// TODO Auto-generated method stub
		
	}
}