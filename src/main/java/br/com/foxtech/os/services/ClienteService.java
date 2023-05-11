package br.com.foxtech.os.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foxtech.os.domain.Cliente;
import br.com.foxtech.os.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;

	public Cliente find(Long id) {

		Optional<Cliente> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
