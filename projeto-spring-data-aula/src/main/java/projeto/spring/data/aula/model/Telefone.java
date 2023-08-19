package projeto.spring.data.aula.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Aamarrando tabela telefone com o usuario 
 * Deve ser feito uma interface no dao.
 * @author hphoe
 *
 */

@Entity //para virar tabela
public class Telefone {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	private String tipo;
	
	@Column(nullable = false) //coluna não pode ser nula
	private String numero;
	
	@ManyToOne(optional = false)
	private UsuarioSpringData usuarioSpringData; //tabela a ser amarrada onde 1 pessoa pode ter muitos telef.

	//set e get
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public UsuarioSpringData getUsuarioSpringData() {
		return usuarioSpringData;
	}

	public void setUsuarioSpringData(UsuarioSpringData usuarioSpringData) {
		this.usuarioSpringData = usuarioSpringData;
	}
	
	

}
