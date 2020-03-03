package br.com.totustuus.financas.main;

import javax.persistence.EntityManager;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.util.JPAUtil;

public class SelecionaConta {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
		em.getTransaction().begin();

		/*
		 * Conta.class � a classe que vou usar para buscar, ser� minha tabela O 1 � o
		 * n�mero do ID que quero buscar. � importante que tipo num�rico seja o mesmo
		 * usado na cria��o.
		 * 
		 */
		Conta conta = em.find(Conta.class, 1);

		/*
		 * Executando a classe, al�m de buscar e imprimir, o Hibernate realizou um
		 * update, que verificamos se est� correto no terminal, digitando select * from
		 * Conta.
		 * 
		 * Como ser� que isto ocorre? A JPA conseguiu sincronizar os dados da
		 * Conta com os do registro do banco de dados.
		 * 
		 * Isto acontece porque o m�todo find() nos devolve uma inst�ncia de Conta
		 * considerado como estado Managed (gerenciado), estado da entidade da JPA cujos
		 * dados s�o automaticamente sincronizados com o banco de dados.
		 * 
		 * Com o JPA, o objetivo � sempre trazer os objetos para o estado Managed, j� que 
		 * assim eles ser�o gerenciados e automaticamente sincronizados com o banco.
		 * 
		 */
		conta.setTitular("Zacarias");
		
		System.out.println(conta.getTitular());
		System.out.println(conta.getBanco());

		em.getTransaction().commit();
		JPAUtil.getEntityManagerFactoty().close();

	}
}
