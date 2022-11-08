package com.fatec.grupox.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //manipula - lazy loaded properties
@Entity
public class Produto {
 @Id
 private Long produtoId;
 private String descricao;
 private double custo;
 private int quantidade;

public Produto(Long id, String descricao, double custo, int quantidade) {
 this.produtoId = id;
 this.descricao = descricao;
 this.quantidade = quantidade;
 this.custo = custo;
 }
 public Produto() {
 }

 public Long getProdutoId() {
 return produtoId;
 }
 public void setProdutoId(Long produtoId) {
 this.produtoId = produtoId;
 }
 public String getDescricao() {
 return descricao;
 }
 public void setDescricao(String descricao) {
 this.descricao = descricao;
 }
 public int getQuantidade() {
 return quantidade;
 }
 public void setQuantidade(int quantidade) {
 this.quantidade = quantidade;
 }
 public double getCusto() {
 return custo;
 }
 public void setCusto(double custo) {
 this.custo = custo;
 }
public void setId(Long id) {
	// TODO Auto-generated method stub
	
}
} 