package br.com.totustuus.financas.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	/*
	 * Agora precisaremos chamar o JPA para de fato persistirmos estes dados no
	 * banco, a partir de @EntityManager, classe principal para a opera��o de tudo
	 * que quisermos em nossas entidades
	 * 
	 * Para instanciarmos o EntityManagerFactory usaremos a classe Persistence, que
	 * representa o persistence.xml, a partir do qual criaremos o
	 * EntityManagerFactory passando o nome da unidade de persist�ncia (aquele que
	 * agrupa todas as configura��es que fizemos, de Provider, conta). Podemos
	 * querer fazer integra��o com o MySQL, com o Postgres, bastando criar mais de
	 * uma unidade de persist�ncia.
	 * 
	 * Em nosso caso, por ora teremos apenas uma, chamada financas, e criaremos uma
	 * EntityManager utilizando a nossa Factory. Feito isto, sabemos que depois que
	 * o usarmos � preciso fech�-lo em nosso sistema. Solicitaremos ao
	 * EntityManager, ou seja, ao JPA, para que esta conta seja persistida, e para
	 * isto, abriremos e comitaremos uma transa��o.
	 */
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
	
	public static EntityManagerFactory getEntityManagerFactoty() {
		return emf;
	}
	
}
