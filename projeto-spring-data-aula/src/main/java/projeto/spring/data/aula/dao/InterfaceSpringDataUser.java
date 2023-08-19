package projeto.spring.data.aula.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import projeto.spring.data.aula.model.UsuarioSpringData;

/**
 * Criação do repositorio onde serão feitos os cruds do sistema (salvar, deletar consultar, etc), 
 * deve ser uma interface..
 * O repositorio deve estar no arquivo de configuração do JPA (spring-config.xml):
 * -> <jpa:repositories base-package="projeto.spring.data.aula.dao" />
 * Deve ter a anotação @Repository e extender do JpaRepository ou CrudRepository,
 *  para cada classe persistente deve haver um repository que recebe  a classe e o tipo de ID.
 * Para usar a  classe de repository devve ser instanciada na classe e anotar com @Autowired.
 * Ex.: @Autowired
		private  InterfaceSpringDataUser interfaceSpringDataUser;
 * 
 * @author hphoe
 *
 */

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long> {
	
	//consultas personalizadas         onde contem
	@Query(value = "select p from UsuarioSpringData p where p.nome like %?1%") //executa no BD e retorna uma lista
	public List<UsuarioSpringData> buscaPorNome(String nome);
	
	
	//@Lock realiza bloqueios para que dois ou mais usuario não mecham enquanto um faz alteração
	@Lock(LockModeType.READ)//bloqueio de leituraenquatoalguem atualiza
	@Transactional (readOnly = true) //indica que é apenas leitura, nunca faz alteração
	@Query(value = "select p from UsuarioSpringData p where p.nome = :paramnome") //busca igual o parametro passado
	public UsuarioSpringData buscaPorNomeParam (@Param("paramnome") String paramnome);
	
	//para sobreescreve um metodo repira-se o @Override e altera-se o nome
	//@Override
	default <S extends UsuarioSpringData> S saveAtual(S entity) {
		// cria-se a rotina aki
		return save(entity);
	}
	
	//sem essas anotaçoes da erro
	@Modifying //anotaação de modificação
	@Transactional //anotação que  abre uma transação
	@Query("delete from UsuarioSpringData u where u.nome = ?1") //nome igual
	public void deletePorNome(String nome);	
	
	@Modifying
	@Transactional
	@Query("update from UsuarioSpringData u set u.email = ?1 where u.nome = ?2") //nome igual
	public void updateEmailPorNome(String email, String nome);	
	
	

}
