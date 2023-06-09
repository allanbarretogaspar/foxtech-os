package br.com.foxtech.os.dto;

import java.io.Serializable;

import br.com.foxtech.os.domain.Peca;
import jakarta.validation.constraints.NotEmpty;

public class PecaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String modelo;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String codigo;
	
	private String variacao;
	private Double precoEntrada;
	private Double precoSaida;
	private Long fornecedorId;
	private Long fabricanteId;
	private String observacoes;
	
	public PecaDTO() {
		
	}

	public PecaDTO(Peca obj) {
		
		this.id = obj.getId();
		this.modelo = obj.getModelo();
		this.nome = obj.getNome();
		this.codigo = obj.getCodigo();
		this.variacao = obj.getVariacao();
		this.precoEntrada = obj.getPrecoEntrada();
		this.precoSaida = obj.getPrecoSaida();
		this.observacoes = obj.getObservacoes();
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

	public Long getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(Long fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public Long getFabricanteId() {
		return fabricanteId;
	}

	public void setFabricanteId(Long fabricanteId) {
		this.fabricanteId = fabricanteId;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
}
