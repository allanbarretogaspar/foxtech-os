package br.com.foxtech.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.foxtech.os.domain.Categoria;
import br.com.foxtech.os.domain.Defeito;
import br.com.foxtech.os.dto.DefeitoDTO;
import br.com.foxtech.os.repositories.DefeitoRepository;
import br.com.foxtech.os.services.exeptions.DataIntegrityException;
import br.com.foxtech.os.services.exeptions.ObjectNotFoundException;

@Service
public class DefeitoService {

	@Autowired
	private DefeitoRepository repo;

	public Defeito find(Long id) {

		Optional<Defeito> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Defeito.class.getName()));
	}

	public Defeito insert(Defeito obj) {

		obj.setId(null);
		repo.save(obj);
		return obj;
	}

	public Defeito update(Defeito obj) {
		Defeito newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {

		try {
			find(id);
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possível excluir, existem entidades relacionadas");
		}
	}

	public List<Defeito> findAll() {

		return repo.findAll();
	}

	public Page<Defeito> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Defeito fromDTO(DefeitoDTO objDto) {

		Categoria cat = new Categoria(objDto.getCategoriaId(), null);
		return new Defeito(objDto.getId(), objDto.getDescricao(), cat);

	}

	private void updateData(Defeito newObj, Defeito obj) {

		newObj.setDescricao(obj.getDescricao());
		newObj.setCategoria(obj.getCategoria());

	}

}
