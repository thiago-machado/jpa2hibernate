package br.com.totustuus.financas.main;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.model.Movimentacao;
import br.com.totustuus.financas.model.TipoMovimentacao;
import br.com.totustuus.financas.util.JPAUtil;

public class TesteJPQL3 {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
		em.getTransaction().begin();

		
		Conta conta = new Conta();
		conta.setId(2);
		
		String jpql = "SELECT sum(m.valor) "
				+ "FROM Movimentacao m "
				+ "WHERE m.conta = :pconta "
				+ "AND m.tipoMovimentacao = :ptipomovimentacao ";
		
		Query query = em.createQuery(jpql);
		query.setParameter("pconta", conta);
		query.setParameter("ptipomovimentacao", TipoMovimentacao.SAIDA);

		BigDecimal soma = (BigDecimal) query.getSingleResult();

		System.out.println(soma);

		em.getTransaction().commit();
		em.close();
	}
}
