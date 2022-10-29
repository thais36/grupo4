package com.fatec.grupo4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Campeonato {
	
	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Nome da Campeonato é requerido")
	private String nome;
	@NotBlank(message = "O sexo é requerido.")
	private String sexo;
	@NotBlank(message = "O dia da semana é requerido.")
	private String diaCampeonato;
	@NotBlank(message = "O Horário é requerido.")
	private String horarioCampeonato;

	// CONSTRUCTORES
	public Campeonato() {
	
	}

	public Campeonato(Long id, @NotBlank(message = "Nome da campeonato é requerido") String nome,
			@NotBlank(message = "O sexo é requerido.") String sexo,
			@NotBlank(message = "O dia da semana é requerido.") String diaCampeonato,
			@NotBlank(message = "O Horário é requerido.") String horarioCampeonato) {
		super();
		this.id = id;
		this.nome = nome;
		this.sexo = sexo;
		this.diaCampeonato = diaCampeonato;
		this.horarioCampeonato = horarioCampeonato;
	}



	// GETTERS E SETTERS
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDiaCampeonato() {
		return diaCampeonato;
	}

	public void setDiaCampeonato(String diaCampeonato) {
		this.diaCampeonato = diaCampeonato;
	}

	public String getHorarioCampeonato() {
		return horarioCampeonato;
	}

	public void setHorarioCampeonato(String horarioCampeonato) {
		this.horarioCampeonato = horarioCampeonato;
	}
}