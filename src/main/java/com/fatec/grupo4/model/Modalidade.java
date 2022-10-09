package com.fatec.grupo4.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Modalidade {
	
	// ATRIBUTOS
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank(message = "Modalidade é requerido")
	private String modalidade;
	@NotBlank(message = "O sexo é requerido.")
	private String sexo;
	@NotBlank(message = "O dia da semana é requerido.")
	private String diaTreino;
	@NotBlank(message = "O Horário é requerido.")
	private String horarioTreino;

	// CONSTRUCTORES
	public Modalidade() {
	
	}
	
	public Modalidade(String modalidade, String sexo, String diaTreino, String horarioTreino) {
		this.modalidade = modalidade;
		this.sexo = sexo;
		this.diaTreino = diaTreino;
		this.horarioTreino = horarioTreino;
	}

	// GETTERS E SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDiaTreino() {
		return diaTreino;
	}

	public void setDiaTreino(String diaTreino) {
		this.diaTreino = diaTreino;
	}

	public String getHorarioTreino() {
		return horarioTreino;
	}

	public void setHorarioTreino(String horarioTreino) {
		this.horarioTreino = horarioTreino;
	}

	
	// CASO SE QUEIRA MEXER COM DATAS (Tipo Date)
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