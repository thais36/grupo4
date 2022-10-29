package com.fatec.grupo4.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.br.CPF;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //manipula - lazy loaded properties
@Entity
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; //numero do pedido
	@CPF
	private String cpf;
	private String dataEmissao;
	@OneToMany
	private List<ItemDePedido> itens = new ArrayList<>();
		
	public Pedido(String cpf) {
		this.cpf = cpf;
	}
	public Pedido() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public double getValorTotal() {
		double soma = 0.0;
		for (ItemDePedido ip : itens) {
			soma = soma + ip.getSubTotal();
		}
		return soma;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getDataEmissao() {
		return dataEmissao;
	}
	public void setDataEmissao(String dataEmissao) {
		this.dataEmissao = dataEmissao;
	}
	public List<ItemDePedido> getItens() {
		return itens;
	}
	public void setItens(List<ItemDePedido> itens) {
		this.itens = itens;
	}
	@Override
	public String toString() {
		return "Pedido [id=" + id + ", cpf=" + cpf + ", dataEmissao=" + dataEmissao + ", itens=" + itens.get(0).toString() + "]";
	}
	
	//equals e toString omitidos
}
