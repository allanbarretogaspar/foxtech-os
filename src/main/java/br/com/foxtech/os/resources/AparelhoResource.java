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

import br.com.foxtech.os.domain.Aparelho;
import br.com.foxtech.os.dto.AparelhoDTO;
import br.com.foxtech.os.services.AparelhoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/aparelhos")
public class AparelhoResource {

	@Autowired
	private AparelhoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Aparelho> find(@PathVariable Long id) {

		Aparelho obj = service.find(id);

		return ResponseEntity.ok().body(obj);
	}

	@PostMapping()
	public ResponseEntity<Void> insert(@Valid @RequestBody AparelhoDTO objDto) {
		Aparelho obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody AparelhoDTO objDto, @PathVariable Long id) {
		Aparelho obj = service.fromDTO(objDto);
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
	public ResponseEntity<List<AparelhoDTO>> findAll() {

		List<Aparelho> list = service.findAll();
		List<AparelhoDTO> listDto = list.stream().map(obj -> new AparelhoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<AparelhoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Aparelho> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<AparelhoDTO> listDto = list.map(obj -> new AparelhoDTO(obj));
		return ResponseEntity.ok().body(listDto);
	}

}
