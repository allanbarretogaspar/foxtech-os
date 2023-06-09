package br.com.foxtech.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.foxtech.os.domain.Fornecedor;
import br.com.foxtech.os.domain.enums.TipoCliente;
import br.com.foxtech.os.dto.FornecedorDTO;
import br.com.foxtech.os.dto.FornecedorNewDTO;
import br.com.foxtech.os.repositories.FornecedorRepository;
import br.com.foxtech.os.services.exeptions.DataIntegrityException;
import br.com.foxtech.os.services.exeptions.ObjectNotFoundException;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository repo;
	

	public Fornecedor find(Long id) {

		Optional<Fornecedor> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Fornecedor.class.getName()));
	}
	
	public Fornecedor insert(Fornecedor obj) {

		obj.setId(null);
		repo.save(obj);
		return obj;
	}
	
	public Fornecedor update(Fornecedor obj) {
		Fornecedor newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}


	public void delete(Long id) {

		try {
			find(id);
			repo.deleteById(id);

		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possível excluir existem entidades relacionadas");
		}
	}

	public List<Fornecedor> findAll() {

		return repo.findAll();
	}

	public Page<Fornecedor> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Fornecedor fromDTO(FornecedorDTO objDto) {

		return new Fornecedor(objDto.getId(), objDto.getNome(),objDto.getEmail(), null, objDto.getSite(), null);
		

	}
	
	public Fornecedor fromDTO(FornecedorNewDTO objDto) {
		
		Fornecedor forn = new Fornecedor(objDto.getId(), objDto.getNome(),objDto.getEmail(), 
				objDto.getCpfOuCnpj(), objDto.getSite(), TipoCliente.toEnum(objDto.getTipo()));
		
		forn.getTelefones().add(objDto.getTelefone1());
		
		if (objDto.getTelefone2()!=null) {
			forn.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			forn.getTelefones().add(objDto.getTelefone3());
		}
		return forn;
	}
	
	private void updateData(Fornecedor newObj, Fornecedor obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
		newObj.setCpfOuCnpj(obj.getCpfOuCnpj());
		newObj.setSite(obj.getSite());
			
	}

}
