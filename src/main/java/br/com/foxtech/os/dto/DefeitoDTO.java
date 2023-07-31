package br.com.foxtech.os.dto;

import java.io.Serializable;

import br.com.foxtech.os.domain.Defeito;
import jakarta.validation.constraints.NotEmpty;

public class DefeitoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento Obrigatório")
	private String descricao;
	private Long categoriaId;
	private String categoriaNome;

	public DefeitoDTO() {

	}

	public DefeitoDTO(Defeito obj) {

		this.id = obj.getId();
		this.descricao = obj.getDescricao();
		this.categoriaId = obj.getCategoria().getId();
		this.categoriaNome = obj.getCategoria().getNome();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoriaNome() {
		return categoriaNome;
	}

	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}

}
