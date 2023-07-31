package br.com.foxtech.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.foxtech.os.domain.Defeito;

@Repository
public interface DefeitoRepository extends JpaRepository<Defeito, Long> {

}
