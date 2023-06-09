package br.com.foxtech.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.foxtech.os.domain.Fabricante;
import br.com.foxtech.os.dto.FabricanteDTO;
import br.com.foxtech.os.repositories.FabricanteRepository;
import br.com.foxtech.os.services.exeptions.DataIntegrityException;
import br.com.foxtech.os.services.exeptions.ObjectNotFoundException;

@Service
public class FabricanteService {
	
	@Autowired
	private FabricanteRepository repo;
	
	public Fabricante find(Long id) {

		Optional<Fabricante> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Fabricante.class.getName()));
	}

	public Fabricante insert(Fabricante obj) {

		obj.setId(null);
		return repo.save(obj);
	}

	public Fabricante update(Fabricante obj) {
		Fabricante newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	public void delete(Long id) {

		try {
			find(id);
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possível excluir uma fabricante que possui aparelhos ou peças cadastradas");
		}
	}

	public List<Fabricante> findAll() {

		return repo.findAll();
	}

	public Page<Fabricante> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Fabricante fromDTO(FabricanteDTO objDto) {

		return new Fabricante(objDto.getId(), objDto.getNome());

	}

	private void updateData(Fabricante newObj, Fabricante obj) {
		newObj.setNome(obj.getNome());

	}

}
