package br.com.foxtech.os.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.foxtech.os.domain.Cidade;
import br.com.foxtech.os.domain.Endereco;
import br.com.foxtech.os.domain.Funcionario;
import br.com.foxtech.os.dto.FuncionarioDTO;
import br.com.foxtech.os.dto.FuncionarioNewDTO;
import br.com.foxtech.os.repositories.EnderecoRepository;
import br.com.foxtech.os.repositories.FuncionarioRepository;
import br.com.foxtech.os.services.exeptions.DataIntegrityException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repo;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public Funcionario find(Long id) {

		Optional<Funcionario> obj = repo.findById(id);
		return obj.orElse(null);
	}
	
	public Funcionario insert(Funcionario obj) {

		obj.setId(null);
		repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Funcionario update(Funcionario obj) {
		Funcionario newObj = find(obj.getId());
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

	public List<Funcionario> findAll() {

		return repo.findAll();
	}

	public Page<Funcionario> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Funcionario fromDTO(FuncionarioDTO objDto) {

		return new Funcionario(objDto.getId(), objDto.getCpf(), objDto.getNome(), objDto.getCargo());
		

	}
	
	public Funcionario fromDTO(FuncionarioNewDTO objDto) {
		
		Funcionario func = new Funcionario(null, objDto.getCpf(), objDto.getNome(),objDto.getCargo());
		
		Cidade cid = new Cidade(objDto.getCidadeId(), null, null);
		
		Endereco end = new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), 
				objDto.getComplemento(), objDto.getBairro(), objDto.getCep(), null, cid, func);
		
		func.getEnderecos().add(end);
		func.getTelefones().add(objDto.getTelefone1());
		
		if (objDto.getTelefone2()!=null) {
			func.getTelefones().add(objDto.getTelefone2());
		}
		if (objDto.getTelefone3()!=null) {
			func.getTelefones().add(objDto.getTelefone3());
		}
		return func;
	}
	
	private void updateData(Funcionario newObj, Funcionario obj) {
		newObj.setCpf(obj.getCpf());
		newObj.setNome(obj.getNome());
		newObj.setCargo(obj.getCargo());
			
	}

}
