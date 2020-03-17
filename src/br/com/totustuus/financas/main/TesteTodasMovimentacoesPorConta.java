package br.com.totustuus.financas.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.util.JPAUtil;

public class TesteTodasMovimentacoesPorConta {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
		em.getTransaction().begin();

		/*
		 * Se quiser buscar todos os clientes e suas movimentações, poderíamos executar:
		 * SELECT * FROM Conta
		 * 
		 * Contudo, toda vez que executássemos conta.getMovimentacoes(), um novo SELECT
		 * seria realizado buscando todas as movimentações.
		 * 
		 * Isso ocorre justamente porque, por padrão, os relacionamentos [...]ToMany,
		 * que são listas ou conjuntos, são considerados Lazy, com
		 * "carregamento preguiçoso". Ou seja, o JPA só irá ao banco para buscar estes
		 * relacionamentos quando você precisa deles
		 * 
		 * Queremos que quando a conta for buscada, sejam trazidas também as
		 * movimentações.
		 * 
		 * Então, precisaremos substituir o comportamento Lazy por Eager Loading, a
		 * partir do parâmetro JOIN FETCH, o que quer dizer que queremos juntar, nesta
		 * Query, a conta e a movimentação.
		 * 
		 * Esse comando SQL retornará todos os clientes e suas movimentações.
		 * 
		 */
		String jpql = "SELECT DISTINCT c FROM Conta c LEFT JOIN FETCH c.movimentacoes";

		Query query = em.createQuery(jpql);

		List<Conta> todasAsContas = query.getResultList();

		for (Conta conta : todasAsContas) {
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Movimentacoes: ");
			System.out.println(conta.getMovimentacoes().size());
		}

		em.close();

	}
}
