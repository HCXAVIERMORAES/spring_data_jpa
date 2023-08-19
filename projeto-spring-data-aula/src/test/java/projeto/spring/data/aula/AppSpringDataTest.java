package projeto.spring.data.aula;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import projeto.spring.data.aula.dao.InterfaceSpringDataUser;
import projeto.spring.data.aula.dao.InterfaceTelefone;
import projeto.spring.data.aula.model.Telefone;
import projeto.spring.data.aula.model.UsuarioSpringData;

/**
 * Para rodara é necessario duas anotações: @RunWith(SpringJUnit4ClassRunner.class) -> passando a essa classe
 * e o @ContextConfiguration para ler o spring passandoa localização 
 * @author hphoe
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"})
public class AppSpringDataTest {
	
	//instanciando a classe de repository
	
	@Autowired
	private  InterfaceSpringDataUser interfaceSpringDataUser;
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;
	
	@Test
	public void testeInsert () {
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData(); //cria-se o objeto
		
		//seta-se os atrributos
		usuarioSpringData.setEmail("javaavancado@javaavancado.com");
		usuarioSpringData.setIdade(30); 
		usuarioSpringData.setLogin("teste1234");
		usuarioSpringData.setSenha("555");
		usuarioSpringData.setNome("Egidio alex");
		
		//passa para a interface e salva
		interfaceSpringDataUser.save(usuarioSpringData);
		
		//contando ususarios cadastrados
		System.out.println("Usuarios cadastrado: "+ interfaceSpringDataUser.count());
		
	}
	
	//consultapor ID
	@Test
	public void testeConsultar () {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L);
		
		System.out.println("email: "+usuarioSpringData.get().getEmail());
		System.out.println("idade: "+usuarioSpringData.get().getIdade());
		System.out.println("login: "+usuarioSpringData.get().getLogin());
		System.out.println("Nome: " +usuarioSpringData.get().getNome());
		System.out.println("senha: "+usuarioSpringData.get().getSenha());
		System.out.println("id: "+usuarioSpringData.get().getId());	
		
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			
			System.out.println("tipo tel.:"+telefone.getTipo());
			System.out.println("numero tel.:"+telefone.getNumero());
			System.out.println("id usuario:"+telefone.getId());
			System.out.println("nome usuario:"+telefone.getUsuarioSpringData().getNome());
			System.out.println("-------------------------------------------");
		}
	}
	
	//consulta todos
	@Test
	public void testeConsultaTodos () {
		
		Iterable<UsuarioSpringData> lista =  interfaceSpringDataUser.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			
			System.out.println(usuarioSpringData.getId());
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println("------------------------------");			
		}		
	}
	
	//fazendo update
	@Test
	public void testeUpdate() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L); //consulta no banco
		
		UsuarioSpringData data = usuarioSpringData.get(); //objeto q veio do BD é armazendo em data
		
		data.setNome("Xavier teste update spring data"); //troca nome
		data.setIdade(46);
		
		interfaceSpringDataUser.save(data);
	}
	
	//teste delete
	@Test
	public void testeDelete() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(5L);
		interfaceSpringDataUser.delete(usuarioSpringData.get()); //deleta usuario consultado
		
		//interfaceSpringDataUser.deleteById(4L);
	}
	
	//consulta personalizada, usando metodo criado na interface
	@Test
	public void testeConsultaNome() {
		
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Felix");
		
		//imprime no console
		for (UsuarioSpringData usuarioSpringData : list) {
					
					System.out.println(usuarioSpringData.getId());
					System.out.println(usuarioSpringData.getEmail());
					System.out.println(usuarioSpringData.getIdade());
					System.out.println(usuarioSpringData.getLogin());
					System.out.println(usuarioSpringData.getNome());
					System.out.println(usuarioSpringData.getSenha());
					System.out.println("------------------------------");			
				}			
	}
	
	@Test
	public void testeConsultaNomeParam() {
		
		UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("helton");
		
		//imprime no console					
		System.out.println(usuarioSpringData.getId());
		System.out.println(usuarioSpringData.getEmail());
		System.out.println(usuarioSpringData.getIdade());
		System.out.println(usuarioSpringData.getLogin());
		System.out.println(usuarioSpringData.getNome());
		System.out.println(usuarioSpringData.getSenha());
		System.out.println("------------------------------");				
	}
	
	//deletar por nome 
	@Test
	public void testeDeletarPorNome() {
		
		interfaceSpringDataUser.deletePorNome("Egidio alex");
	}
	
	@Test
	public void testeUpdateEmailPorNome() {
		interfaceSpringDataUser.updateEmailPorNome("teste@mail.com", "helton");		
	}
	
	//inserindo telefone
	@Test
	public void testeInsertTelefone() {
		
		//consultando usuario
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("Casa");
		telefone.setNumero("313595-9969");
		telefone.setUsuarioSpringData(usuarioSpringData.get()); //passa usuario consultado
		
		interfaceTelefone.save(telefone); //salvando
	}

}
