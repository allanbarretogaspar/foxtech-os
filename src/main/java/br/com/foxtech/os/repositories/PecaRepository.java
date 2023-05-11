package br.com.foxtech.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.foxtech.os.domain.Peca;

public interface PecaRepository extends JpaRepository<Peca, Long> {

}
