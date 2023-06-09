package br.com.foxtech.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.foxtech.os.domain.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
	
	@Transactional(readOnly = true)
	Fornecedor findByEmail(String email);

}
