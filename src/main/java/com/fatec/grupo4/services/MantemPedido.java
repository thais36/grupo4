package com.fatec.grupo4.services;

import java.util.List;
import java.util.Optional;

import com.fatec.grupo4.model.Pedido;
import com.fatec.grupo4.model.PedidoDTO;
public interface MantemPedido {
	public Optional <Pedido> buscaPorId (Long id);
	public List<Pedido> buscaPorCpf (String cpf);
	public List<Pedido> consultaTodos();
	public void excluiPedido(Long id);
	public Pedido cadastrarPedido(PedidoDTO pedidoDTO);
	public boolean consultaPorCpf(String cpf);
}
