package br.com.totustuus.financas.main;

import javax.persistence.EntityManager;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.util.JPAUtil;

public class SelecionaConta {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
		em.getTransaction().begin();

		/*
		 * Conta.class é a classe que vou usar para buscar, será minha tabela O 1 é o
		 * número do ID que quero buscar. É importante que tipo numérico seja o mesmo
		 * usado na criação.
		 * 
		 */
		Conta conta = em.find(Conta.class, 1);

		/*
		 * Executando a classe, além de buscar e imprimir, o Hibernate realizou um
		 * update, que verificamos se está correto no terminal, digitando select * from
		 * Conta.
		 * 
		 * Como será que isto ocorre? A JPA conseguiu sincronizar os dados da
		 * Conta com os do registro do banco de dados.
		 * 
		 * Isto acontece porque o método find() nos devolve uma instância de Conta
		 * considerado como estado Managed (gerenciado), estado da entidade da JPA cujos
		 * dados são automaticamente sincronizados com o banco de dados.
		 * 
		 * Com o JPA, o objetivo é sempre trazer os objetos para o estado Managed, já que 
		 * assim eles serão gerenciados e automaticamente sincronizados com o banco.
		 * 
		 */
		conta.setTitular("Zacarias");
		
		System.out.println(conta.getTitular());
		System.out.println(conta.getBanco());

		em.getTransaction().commit();
		JPAUtil.getEntityManagerFactoty().close();

	}
}
