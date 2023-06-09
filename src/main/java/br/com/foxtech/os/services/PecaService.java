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
import br.com.foxtech.os.domain.Fornecedor;
import br.com.foxtech.os.domain.Peca;
import br.com.foxtech.os.dto.PecaDTO;
import br.com.foxtech.os.repositories.PecaRepository;
import br.com.foxtech.os.services.exeptions.DataIntegrityException;
import br.com.foxtech.os.services.exeptions.ObjectNotFoundException;

@Service
public class PecaService {
	
	@Autowired
	private PecaRepository repo;
	
	public Peca find(Long id) {

		Optional<Peca> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Peca.class.getName()));
	}
	
	public Peca insert(Peca obj) {

		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public Peca update(Peca obj) {
		Peca newObj = find(obj.getId());
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

	public List<Peca> findAll() {

		return repo.findAll();
	}

	public Page<Peca> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Peca fromDTO(PecaDTO objDto) {

		Fabricante fab = new Fabricante(objDto.getFabricanteId(), null);
		Fornecedor forn = new Fornecedor(objDto.getFornecedorId(), null, null, null, null, null);
		return new Peca(objDto.getId(), objDto.getModelo(), objDto.getNome(), objDto.getCodigo(), 
				objDto.getVariacao(), objDto.getPrecoEntrada(), objDto.getPrecoSaida(), forn, fab, objDto.getObservacoes());	

	}
	
	
	private void updateData(Peca newObj, Peca obj) {
		
		newObj.setModelo(obj.getModelo());
		newObj.setNome(obj.getNome());
		newObj.setCodigo(obj.getCodigo());
		newObj.setVariacao(obj.getVariacao());
		newObj.setPrecoEntrada(obj.getPrecoEntrada());
		newObj.setPrecoSaida(obj.getPrecoSaida());
		newObj.setObservacoes(obj.getObservacoes());
			
	}

}
