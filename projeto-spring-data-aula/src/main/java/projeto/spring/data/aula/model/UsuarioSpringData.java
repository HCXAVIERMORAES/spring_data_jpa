package projeto.spring.data.aula.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Criando entidade e tabela no banco de dados.
 * @Entity intica que é uma classe de persistencia, devee ter um id inticado  por @Id
 * @GeneratedValue(strategy = GenerationType.AUTO) -. indica que o id será gerado automaticamente - tipo de strategia
 * o pacote  deve ser integrado ao persistence.xml at´chegar na classe assim: 
 *   <class>projeto.spring.data.aula.model.UsuarioSpringData</class>. idem para toda classe de persistencia
 * Com isso ao subir o projeto toda a classe que for de pessitencia deve ser criada no  banco.
 * @author hphoe
 *
 */

@Entity
public class UsuarioSpringData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String  login;
	private String  senha;
	private String  nome;
	private String  email;
	private int idade;
	
	//para carregar os telefones @OneToMany -> um para muitos fetch = FetchType.EAGER-> para carregar na ora q se carrega o objeto
	@OneToMany(mappedBy = "usuarioSpringData", orphanRemoval = true, fetch = FetchType.EAGER) //mapea o objeto do telefone. orphanRemoval = true remove usuario e telefones	
	private List<Telefone> telefones;
	
	//set e get
	
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	

}
