package br.com.totustuus.financas.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.model.TipoMovimentacao;
import br.com.totustuus.financas.util.JPAUtil;

public class TesteJPQL2 {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);

		/*
		 * avg(m.valor) = realiza a m�dia. Essa fun��o retorna um Double Agrupando por
		 * data
		 */

		String jpql = "SELECT avg(m.valor) " 
				+ "FROM Movimentacao m " 
				+ "WHERE m.conta = :pConta "
				+ "AND m.tipoMovimentacao = :pTipo " 
				+ "GROUP BY DAY(m.data), MONTH(m.data), YEAR(m.data) ";

		/*
		 * Para assegurar o bom funcionamento, o JPA possui uma vers�o do objeto Query
		 * que permite trabalharmos de forma fortemente tipada: TypedQuery, pelo qual
		 * indicamos explicitamente o tipo de query que ser� retornado. No nosso caso
		 * ser� o Double. Agora, al�m de jpql, precisaremos passar o tipo com que
		 * realmente trabalhamos, Double.class.
		 */
		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		List<Double> medias = query.getResultList();

		System.out.println("A m�dia do dia 13 �: " + medias.get(0));
		System.out.println("A m�dia do dia 14 �: " + medias.get(1));

		em.getTransaction().commit();
		em.close();
	}
}
