package br.com.totustuus.financas.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	/*
	 * Agora precisaremos chamar o JPA para de fato persistirmos estes dados no
	 * banco, a partir de @EntityManager, classe principal para a operação de tudo
	 * que quisermos em nossas entidades
	 * 
	 * Para instanciarmos o EntityManagerFactory usaremos a classe Persistence, que
	 * representa o persistence.xml, a partir do qual criaremos o
	 * EntityManagerFactory passando o nome da unidade de persistência (aquele que
	 * agrupa todas as configurações que fizemos, de Provider, conta). Podemos
	 * querer fazer integração com o MySQL, com o Postgres, bastando criar mais de
	 * uma unidade de persistência.
	 * 
	 * Em nosso caso, por ora teremos apenas uma, chamada financas, e criaremos uma
	 * EntityManager utilizando a nossa Factory. Feito isto, sabemos que depois que
	 * o usarmos é preciso fechá-lo em nosso sistema. Solicitaremos ao
	 * EntityManager, ou seja, ao JPA, para que esta conta seja persistida, e para
	 * isto, abriremos e comitaremos uma transação.
	 */
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
	
	public static EntityManagerFactory getEntityManagerFactoty() {
		return emf;
	}
	
}
