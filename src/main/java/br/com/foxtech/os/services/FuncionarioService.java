package br.com.foxtech.os.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foxtech.os.domain.Funcionario;
import br.com.foxtech.os.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repo;

	public Funcionario find(Long id) {

		Optional<Funcionario> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
