package br.com.foxtech.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.foxtech.os.domain.Aparelho;
import br.com.foxtech.os.domain.Categoria;
import br.com.foxtech.os.domain.Fabricante;
import br.com.foxtech.os.dto.AparelhoDTO;
import br.com.foxtech.os.repositories.AparelhoRepository;
import br.com.foxtech.os.services.exeptions.DataIntegrityException;
import br.com.foxtech.os.services.exeptions.ObjectNotFoundException;

@Service
public class AparelhoService {
	
	@Autowired
	private AparelhoRepository repo;
	
	public Aparelho find(Long id) {

		Optional<Aparelho> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Aparelho.class.getName()));
	}
	
	public Aparelho insert(Aparelho obj) {

		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public Aparelho update(Aparelho obj) {
		Aparelho newObj = find(obj.getId());
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

	public List<Aparelho> findAll() {

		return repo.findAll();
	}

	public Page<Aparelho> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Aparelho fromDTO(AparelhoDTO objDto) {

		Fabricante fab = new Fabricante(objDto.getFabricanteId(), null);
		Categoria cat = new Categoria(objDto.getCategoriaId(), null);
		return new Aparelho(objDto.getId(), objDto.getModelo(), fab, cat, objDto.getObservacoes());	

	}
	
	
	private void updateData(Aparelho newObj, Aparelho obj) {
		
		newObj.setModelo(obj.getModelo());
		newObj.setObservacoes(obj.getObservacoes());
		newObj.setCategoria(obj.getCategoria());
		newObj.setFabricante(obj.getFabricante());
			
	}

}
