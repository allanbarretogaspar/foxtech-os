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
import br.com.foxtech.os.dto.CategoriaDTO;
import br.com.foxtech.os.repositories.CategoriaRepository;
import br.com.foxtech.os.services.exeptions.DataIntegrityException;
import br.com.foxtech.os.services.exeptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria find(Long id) {

		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {

		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {

		try {
			find(id);
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possível excluir uma categoria que possui aparelhos");
		}
	}

	public List<Categoria> findAll() {

		return repo.findAll();
	}

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Categoria fromDTO(CategoriaDTO objDto) {

		return new Categoria(objDto.getId(), objDto.getNome());

	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());

	}

}