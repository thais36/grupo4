package com.fatec.grupox.services;

import java.util.List;
import java.util.Optional;
import com.fatec.grupox.model.Pedido;
import com.fatec.grupox.model.PedidoDTO;

public interface MantemPedido {
	public Optional<Pedido> buscaPorId(Long id);

	public List<Pedido> buscaPorCpf(String cpf);

	public List<Pedido> consultaTodos();

	public void excluiPedido(Long id);

	public Pedido cadastrarPedido(Pedido pedido);

	public boolean isClienteCadastrado(String cpf);

	Pedido cadastrarPedido(PedidoDTO pedidoDTO);

	boolean consultaPorCpf(String cpf);
}