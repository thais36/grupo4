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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@Entity
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Nome é requerido")
	private String nome;	
	private String dataEvento;
	private String horaEvento;
	private String dataCadastro;
	@NotBlank(message = "O CEP é obrigatório.")
	private String cep;
	private String endereco;
	@NotBlank(message = "O complemento deve ser informado")
	private String complemento;
	@NotBlank(message = "A categoria é um atributo requerido.")
	private String categoria;

	public Evento(String nome, String dataEvento, String horaEvento, String cep, String complemento, String categoria) {
		this.nome = nome;
		setHoraEvento(dataEvento);
		setHoraEvento(horaEvento);
		setDataCadastro(new DateTime());
		this.cep = cep;
		this.complemento = complemento;
		this.categoria = categoria;
	}

	public Evento() {
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

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(DateTime dataAtual) {
		this.dataCadastro = obtemDataAtual(dataAtual);
	}

	public String getDataEvento() {
		return dataEvento;
	}

	public void setDataEvento(String dataEvento) {
		if (validaData(dataEvento) == true) {
			this.dataEvento = dataEvento;
		} else {
			throw new IllegalArgumentException("Data invalida");
		}
	}

	public String getHoraEvento() {
		return horaEvento;
	}
	
	public void setHoraEvento(String horaEvento) {
		this.horaEvento = horaEvento;
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

	public String getCategoria1() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public String obtemDataAtual(DateTime dataAtual) {
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");
		return dataAtual.toString(fmt);
	}
// equals e tostring omitidos

	public String getCategoria() {
		// TODO Auto-generated method stub
		return categoria;
	}

	public void setCategoria(Object categoria2) {
		// TODO Auto-generated method stub
		
	}
}