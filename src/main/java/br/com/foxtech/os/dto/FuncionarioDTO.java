package br.com.foxtech.os.dto;

import java.io.Serializable;

import br.com.foxtech.os.domain.Funcionario;

public class FuncionarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cpf;
	private String nome;
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
