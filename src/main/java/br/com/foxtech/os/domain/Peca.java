package br.com.foxtech.os.domain;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Peca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String modelo;
	private String nome;
	private String codigo;
	private String variacao;
	private Double precoEntrada;
	private Double precoSaida;
	
	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Fornecedor fornecedor;

	@ManyToOne
	@JoinColumn(name = "fabricante_id")
	private Fabricante fabricante;
	
	private String observacoes;

	public Peca() {

	}

	

	public Peca(Long id, String modelo, String nome, String codigo, String variacao, Double precoEntrada,
			Double precoSaida, Fornecedor fornecedor, Fabricante fabricante, String observacoes) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.nome = nome;
		this.codigo = codigo;
		this.variacao = variacao;
		this.precoEntrada = precoEntrada;
		this.precoSaida = precoSaida;
		this.fornecedor = fornecedor;
		this.fabricante = fabricante;
		this.observacoes = observacoes;
	}
	
	



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getModelo() {
		return modelo;
	}



	public void setModelo(String modelo) {
		this.modelo = modelo;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getVariacao() {
		return variacao;
	}



	public void setVariacao(String variacao) {
		this.variacao = variacao;
	}



	public Double getPrecoEntrada() {
		return precoEntrada;
	}



	public void setPrecoEntrada(Double precoEntrada) {
		this.precoEntrada = precoEntrada;
	}



	public Double getPrecoSaida() {
		return precoSaida;
	}



	public void setPrecoSaida(Double precoSaida) {
		this.precoSaida = precoSaida;
	}



	public Fornecedor getFornecedor() {
		return fornecedor;
	}



	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}



	public Fabricante getFabricante() {
		return fabricante;
	}



	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}



	public String getObservacoes() {
		return observacoes;
	}



	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}



	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peca other = (Peca) obj;
		return Objects.equals(id, other.id);
	}


}
