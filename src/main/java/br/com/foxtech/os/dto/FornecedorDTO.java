package br.com.foxtech.os.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import br.com.foxtech.os.domain.Fornecedor;
import br.com.foxtech.os.services.validation.FornecedorUpdate;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@FornecedorUpdate
public class FornecedorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Length(min = 5, max = 150, message = "O tamanho deve ser entre 5 e 150 caracteres")
	private String nome;

	@NotEmpty(message = "Preenchimento Obrigatório")
	@Email()
	private String email;
	private String site;

	public FornecedorDTO() {

	}

	public FornecedorDTO(Fornecedor obj) {

		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.site = obj.getSite();

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
