package com.fatec.grupo4.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CNPJ;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class Fornecedor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Razão Social é requerida")
	private String razaoSocial;
	private String dataCadastro;
	@CNPJ(message = "CNPJ invalido.")
	@Column(unique = true) // nao funciona com @Valid tem que tratar na camada de persistencia
	private String cnpj;
	//@NotBlank(message = "O CEP é obrigatorio.")
	private String cep;
	private String endereco;
	//@NotBlank(message = "O complemento deve ser informado")
	private String complemento;
	@NotBlank(message = "A categoria é um atributo requerido.")
	private String categoria;

	public Fornecedor(String razaoSocial, String cnpj, String cep, String complemento, String categoria) {
		this.razaoSocial = razaoSocial;
		setDataCadastro(new DateTime());
		this.cnpj = cnpj;
		this.cep = cep;
		this.complemento = complemento;
		this.categoria = categoria;
	}

	public Fornecedor() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(DateTime dataAtual) {
		this.dataCadastro = obtemDataAtual(dataAtual);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public boolean validaData(String data) {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		df.setLenient(false); //
		try {
			df.parse(data); // data v�lida (exemplo 30 fev - 31 nov)
			return true;
		} catch (ParseException ex) {
			return false;
		}
	}

	public String obtemDataAtual(DateTime dataAtual) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		return dataAtual.toString(fmt);
	}
// equals e tostring omitidos

}