package br.com.foxtech.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.foxtech.os.domain.Aparelho;

public interface AparelhoRepository extends JpaRepository<Aparelho, Long> {

}
