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
		 * Se quiser buscar todos os clientes e suas movimenta��es, poder�amos executar:
		 * SELECT * FROM Conta
		 * 
		 * Contudo, toda vez que execut�ssemos conta.getMovimentacoes(), um novo SELECT
		 * seria realizado buscando todas as movimenta��es.
		 * 
		 * Isso ocorre justamente porque, por padr�o, os relacionamentos [...]ToMany,
		 * que s�o listas ou conjuntos, s�o considerados Lazy, com
		 * "carregamento pregui�oso". Ou seja, o JPA s� ir� ao banco para buscar estes
		 * relacionamentos quando voc� precisa deles
		 * 
		 * Queremos que quando a conta for buscada, sejam trazidas tamb�m as
		 * movimenta��es.
		 * 
		 * Ent�o, precisaremos substituir o comportamento Lazy por Eager Loading, a
		 * partir do par�metro JOIN FETCH, o que quer dizer que queremos juntar, nesta
		 * Query, a conta e a movimenta��o.
		 * 
		 * Esse comando SQL retornar� todos os clientes e suas movimenta��es.
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
