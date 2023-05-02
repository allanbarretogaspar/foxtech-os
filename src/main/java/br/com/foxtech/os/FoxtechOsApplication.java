package br.com.foxtech.os;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.foxtech.os.domain.Aparelho;
import br.com.foxtech.os.domain.Categoria;
import br.com.foxtech.os.domain.Fabricante;
import br.com.foxtech.os.repositories.AparelhoRepository;
import br.com.foxtech.os.repositories.CategoriaRepository;
import br.com.foxtech.os.repositories.FabricanteRepository;

@SpringBootApplication
public class FoxtechOsApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	@Autowired
	private AparelhoRepository aparelhoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FoxtechOsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Smartphone");
		Categoria cat2 = new Categoria(null, "Notebook");
		
		
		
		Fabricante fab1 = new Fabricante(null, "Apple");
		Fabricante fab2 =  new Fabricante(null, "Samsung");
		Fabricante fab3 =  new Fabricante(null, "Amazon");
		
		
		
		Aparelho ap1 = new Aparelho(null, "IPhone 11", fab1, cat1, null);
		Aparelho ap2 = new Aparelho(null, "Galaxy S20 FE 5G", fab2, cat1, null);
		Aparelho ap3 = new Aparelho(null, "MacBook air 11", fab3, cat2, null);
		
		fab1.getAparelhos().addAll(Arrays.asList(ap1));
		fab2.getAparelhos().addAll(Arrays.asList(ap2));
		fab3.getAparelhos().addAll(Arrays.asList(ap3));
		
		cat1.getAparelhos().addAll(Arrays.asList(ap1, ap2));
		cat2.getAparelhos().addAll(Arrays.asList(ap3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		fabricanteRepository.saveAll(Arrays.asList(fab1, fab2));
		aparelhoRepository.saveAll(Arrays.asList(ap1, ap2));
		
		
	}

}
