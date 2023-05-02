package br.com.foxtech.os.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foxtech.os.domain.Fabricante;
import br.com.foxtech.os.repositories.FabricanteRepository;

@Service
public class FabricanteService {
	
	@Autowired
	private FabricanteRepository repository;
	
	public Fabricante find(Long id) {
		
		Optional<Fabricante> obj = repository.findById(id);
		
		return obj.orElse(null);
	}

}
