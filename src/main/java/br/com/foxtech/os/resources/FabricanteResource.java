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

import br.com.foxtech.os.domain.Fabricante;
import br.com.foxtech.os.dto.FabricanteDTO;
import br.com.foxtech.os.services.FabricanteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/fabricantes")
public class FabricanteResource {
	
	@Autowired
	private FabricanteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Fabricante> find(@PathVariable Long id) {
		
		Fabricante obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody FabricanteDTO objDto){
		Fabricante obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody FabricanteDTO objDto, @PathVariable Long id){
		Fabricante obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<FabricanteDTO>> findAll() {
		
		List<Fabricante> list = service.findAll();
		List<FabricanteDTO> listDto = list.stream().map(obj -> new FabricanteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}


	@GetMapping(value = "/page")
	public ResponseEntity<Page<FabricanteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome")String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC")String direction) {
		
		Page<Fabricante> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<FabricanteDTO> listDto = list.map(obj -> new FabricanteDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
