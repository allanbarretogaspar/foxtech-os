package br.com.foxtech.os.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @author allan.gaspar
	 * indica o tipo do aparelho se notebook ou smartphone etc
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	
	
	@OneToMany(mappedBy = "categoria")
	private List<Aparelho> aparelhos = new ArrayList<>();
	
	@OneToMany(mappedBy = "categoria")
	private List<Defeito> defeitos = new ArrayList<>();
	
	
	
	public Categoria() {
		
	}

	public Categoria(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public List<Aparelho> getAparelhos() {
		return aparelhos;
	}

	public void setAparelhos(List<Aparelho> aparelhos) {
		this.aparelhos = aparelhos;
	}

	public List<Defeito> getDefeitos() {
		return defeitos;
	}

	public void setDefeitos(List<Defeito> defeitos) {
		this.defeitos = defeitos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
