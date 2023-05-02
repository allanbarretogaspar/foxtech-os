package br.com.foxtech.os.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.foxtech.os.domain.Aparelho;
import br.com.foxtech.os.repositories.AparelhoRepository;

@Service
public class AparelhoService {
	
	@Autowired
	private AparelhoRepository repository;
	
	public Aparelho find(Long id) {
		
		Optional<Aparelho> obj = repository.findById(id);
		
		return obj.orElse(null);
		
	}

}
