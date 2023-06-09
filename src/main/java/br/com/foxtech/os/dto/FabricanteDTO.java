package br.com.foxtech.os.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.com.foxtech.os.domain.Fabricante;
import jakarta.validation.constraints.NotEmpty;

public class FabricanteDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 80 caracteres")
	private String nome;
	
	public FabricanteDTO() {
		
	}
	
	public FabricanteDTO(Fabricante obj) {
		
		id = obj.getId();
		nome = obj.getNome();
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
		
}
