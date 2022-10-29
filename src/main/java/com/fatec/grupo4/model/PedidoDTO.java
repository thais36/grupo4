package com.fatec.grupo4.model;

public class PedidoDTO {
	String cpf;
	String produtoId;
	String quantidade;

	public PedidoDTO(String cpf, String produtoId, String quantidade) {
		this.cpf = cpf;
		this.produtoId = produtoId;
		this.quantidade = quantidade;
	}

	public PedidoDTO() {
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getProdutoId() {
		return produtoId;
	}

	public void setProdutoId(String produtoId) {
		this.produtoId = produtoId;
	}

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	// equals e toString omitidos
}
