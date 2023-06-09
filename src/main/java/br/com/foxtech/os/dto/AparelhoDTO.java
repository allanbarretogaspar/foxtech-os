package br.com.foxtech.os.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.com.foxtech.os.domain.Aparelho;
import jakarta.validation.constraints.NotEmpty;

public class AparelhoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 2, max = 80, message = "O tamanho deve ser entre 2 e 80 caracteres")
	private String modelo;

	private Long fabricanteId;
	private String fabricanteNome;
	private Long categoriaId;
	private String categoriaNome;
	private String observacoes;

	public AparelhoDTO() {

	}

	public AparelhoDTO(Aparelho obj) {

		this.id = obj.getId();
		this.modelo = obj.getModelo();
		this.categoriaNome = obj.getCategoria().getNome();
		this.fabricanteNome = obj.getFabricante().getNome();
		this.categoriaId = obj.getCategoria().getId();
		this.fabricanteId = obj.getFabricante().getId();
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

	public Long getFabricanteId() {
		return fabricanteId;
	}

	public void setFabricanteId(Long fabricanteId) {
		this.fabricanteId = fabricanteId;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getFabricanteNome() {
		return fabricanteNome;
	}

	public void setFabricanteNome(String fabricanteNome) {
		this.fabricanteNome = fabricanteNome;
	}

	public String getCategoriaNome() {
		return categoriaNome;
	}

	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}

}
