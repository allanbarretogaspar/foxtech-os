package br.com.foxtech.os.dto;

import java.io.Serializable;

import br.com.foxtech.os.services.validation.FornecedorInsert;
import jakarta.validation.constraints.NotEmpty;

@FornecedorInsert
public class FornecedorNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private String site;
	private Integer tipo;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	private String telefone1;
	
	private String telefone2;
	
	private String telefone3;
	
	public FornecedorNewDTO() {
		
	}

	/*public FornecedorNewDTO(Fornecedor obj) {

		this.id = obj.getId();
		this.nome = obj.getNome();
		this.email = obj.getEmail();
		this.cpfOuCnpj = obj.getCpfOuCnpj();
		this.site = obj.getSite();
	}*/
	
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}
	
	
}
