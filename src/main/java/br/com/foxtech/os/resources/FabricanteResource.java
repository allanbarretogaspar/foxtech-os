package br.com.foxtech.os.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.foxtech.os.domain.Fabricante;
import br.com.foxtech.os.services.FabricanteService;

@RestController
@RequestMapping(value = "/fabricantes")
public class FabricanteResource {
	
	@Autowired
	private FabricanteService service;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Fabricante> buscarPorId(@PathVariable Long id){
		
		Fabricante obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
	}

}
