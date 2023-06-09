package br.com.foxtech.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.foxtech.os.domain.Funcionario;
import br.com.foxtech.os.dto.FuncionarioDTO;
import br.com.foxtech.os.dto.FuncionarioNewDTO;
import br.com.foxtech.os.services.FuncionarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Funcionario> listar(@PathVariable Long id) {
		
		Funcionario obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody FuncionarioNewDTO objDto){
		Funcionario obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody FuncionarioDTO objDto, @PathVariable Long id){
		Funcionario obj = service.fromDTO(objDto);
		obj.setId(id); 
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping()
	public ResponseEntity<List<FuncionarioDTO>> findAll() {
		
		List<Funcionario> list = service.findAll();
		List<FuncionarioDTO> listDto = list.stream().map(obj -> new FuncionarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}


	@GetMapping(value = "/page")
	public ResponseEntity<Page<FuncionarioDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction) {
		
		Page<Funcionario> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<FuncionarioDTO> listDto = list.map(obj -> new FuncionarioDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}


}
