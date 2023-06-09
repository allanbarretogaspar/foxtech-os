package br.com.foxtech.os.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import br.com.foxtech.os.domain.Funcionario;
import jakarta.validation.constraints.NotEmpty;

public class FuncionarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@CPF
	private String cpf;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 2, max = 60, message = "O tamanho deve ser entre 2 e 60 caracteres")
	private String cargo;
	
	public FuncionarioDTO() {
		
	}
	
	public FuncionarioDTO(Funcionario obj) {
		id = obj.getId();
		cpf = obj.getCpf();
		nome = obj.getNome();
		cargo = obj.getCargo();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
