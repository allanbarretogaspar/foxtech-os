package br.com.foxtech.os.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foxtech.os.domain.Categoria;
import br.com.foxtech.os.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Long id) {

		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}

}
