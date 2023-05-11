package br.com.foxtech.os;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.foxtech.os.domain.Aparelho;
import br.com.foxtech.os.domain.Categoria;
import br.com.foxtech.os.domain.Cidade;
import br.com.foxtech.os.domain.Cliente;
import br.com.foxtech.os.domain.Endereco;
import br.com.foxtech.os.domain.Estado;
import br.com.foxtech.os.domain.Fabricante;
import br.com.foxtech.os.domain.Funcionario;
import br.com.foxtech.os.domain.enums.TipoCliente;
import br.com.foxtech.os.repositories.AparelhoRepository;
import br.com.foxtech.os.repositories.CategoriaRepository;
import br.com.foxtech.os.repositories.CidadeRepository;
import br.com.foxtech.os.repositories.ClienteRepository;
import br.com.foxtech.os.repositories.EnderecoRepository;
import br.com.foxtech.os.repositories.EstadoRepository;
import br.com.foxtech.os.repositories.FabricanteRepository;
import br.com.foxtech.os.repositories.FuncionarioRepository;

@SpringBootApplication
public class FoxtechOsApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private FabricanteRepository fabricanteRepository;
	
	@Autowired
	private AparelhoRepository aparelhoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
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
		
		Estado est1 = new Estado(null, "Minas Gerais", "MG");
		Estado est2 = new Estado(null, "São Paulo", "SP");
		Estado est3 = new Estado(null, "Distrito Federal", "DF");

		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Brasília", est3);
		

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		est3.getCidades().addAll(Arrays.asList(c4));

		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "993838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1, null);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2, null);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		Funcionario func1 = new Funcionario(null, "65823135254", "Alan Portela", "Diretor");
		Endereco e3 = new Endereco(null, "SBN Quadra 2", "125", "Apto 302", "Asa Norte", "70040-020", null, c4, func1);
		func1.getEnderecos().addAll(Arrays.asList(e3));
		
		funcionarioRepository.saveAll(Arrays.asList(func1));
		enderecoRepository.saveAll(Arrays.asList(e3));
		
		
		
	}

}
