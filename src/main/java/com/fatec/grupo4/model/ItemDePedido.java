package com.fatec.grupo4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class ItemDePedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	@OneToOne  //cada item do pedido esta associado a um produto 
    	@JoinColumn(name="produtoId")
	Produto produto;
	@NotNull
	int quantidade;
	public ItemDePedido(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}
	public ItemDePedido() {
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public double getSubTotal() {
		return quantidade * getProduto().getCusto();
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	@Override
	public String toString() {
		return "ItemDePedido [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + "]";
	}
	
}
