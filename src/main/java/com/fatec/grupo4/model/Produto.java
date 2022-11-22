package com.fatec.grupo4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Nome � requerido")
	private String nome;
	@NotBlank(message = "Nome � requerido")
	private String categoria;
	@NotBlank(message = "categoria � requerido")
	private String preco;
	@NotBlank(message = "Pre�o � requerido")
	private String qntidadedeitens;
	@NotBlank(message = "Quantidade de itens � requerido")
	private String tamanho;
	@NotBlank(message = "Tamanho � requerido")
	private String descricao;
	@NotBlank(message = "Descri��o � requerida")
	private String cor;
	@NotBlank(message = "Cor � requerido")
	

	public Produto(String nome, String categoria, String preco, String qntidadedeitens, String tamanho, String descricao, String cor) {
		this.nome = nome;
		this.categoria = categoria;
		this.preco = preco ;
		this.qntidadedeitens = qntidadedeitens;
		this.tamanho = tamanho;
		this.descricao = descricao;
		this.cor = cor;
	}

	public Produto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getPreco() {
		return preco;
	}

	public void setPreco(String preco) {
		this.preco = preco;
	}

	public void setQntidadedeitens(String qntidadedeitens) {
		this.qntidadedeitens = qntidadedeitens;
	}

	public String getQntidadedeitens() {
		return qntidadedeitens;
	}

	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}

	public String getTamanho() {
		return tamanho;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public @NotNull int getCusto() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Produto getProdutoId() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setProdutoId(long parseLong) {
		// TODO Auto-generated method stub
		
	}

//	public boolean validaData(String data) {
//		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
//		df.setLenient(false); //
//		try {
//			df.parse(data); // data válida (exemplo 30 fev - 31 nov)
//			return true;
//		} catch (ParseException ex) {
//			return false;
//		}
//	}
//
//	public String obtemDataAtual(DateTime dataAtual) {
//		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
//		return dataAtual.toString(fmt);
//	}

	// equals e tostring omitidos

}