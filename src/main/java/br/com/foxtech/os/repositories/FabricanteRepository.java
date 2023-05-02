package br.com.foxtech.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.foxtech.os.domain.Fabricante;

public interface FabricanteRepository extends JpaRepository<Fabricante, Long> {

}
