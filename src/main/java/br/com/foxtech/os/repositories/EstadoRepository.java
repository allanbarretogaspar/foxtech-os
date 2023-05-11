package br.com.foxtech.os.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.foxtech.os.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

}
