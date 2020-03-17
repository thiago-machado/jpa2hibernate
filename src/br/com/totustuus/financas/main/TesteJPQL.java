package br.com.totustuus.financas.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.model.Movimentacao;
import br.com.totustuus.financas.model.TipoMovimentacao;
import br.com.totustuus.financas.util.JPAUtil;

public class TesteJPQL {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);

		/*
		 * Não trabalhamos mais com o modelo relacional, mas com o mundo 
		 * Orientado a Objeto.
		 * 
		 * A palavra Movimentacao está relacionada a Entity/classe Movimentacao.
		 * Não podemos colocar Movimentacao com "m" minúsculo! Devemos escrever o 
		 * nome da Entity igualzinho está na classe.
		 * 
		 * SELECT m =  estamos selecionando tudo o que Movimentação possui.
		 * 
		 * "m.conta.id" = significa que à partir da classe Movimentação (m), estamos 
		 * acessando o atributo "conta" e por fim acessa o atributo "id" que pertence a conta.
		 * Nesse caso, settamos o parâmetro da seguinte maneira:
		 * query.setParameter("pconta", conta);
		 * 
		 * (m.conta = :pconta) = estamos informando que usaremos o atributo conta como parâmetro.
		 * O JPA Hibernate entende que queremos fazer uma selecao usando o ID de conta apenas 
		 * com essa informação na Query. (particularmente, achei melhor o modo utilizado acima)
		 * 
		 * 
		 * 
		 * :pconta = essa é a forma que definimos um parâmetro. Inserimos :nomeDoParamatro.
		 * 
		 */
		String jpql = "SELECT m "
				+ "FROM Movimentacao m "
				+ "WHERE m.conta = :pconta "
				+ "AND m.tipoMovimentacao = :ptipomovimentacao ";
		
		/*
		 * Query = nos permite criar queries SQL.
		 * 
		 * query.setParameter = definimos os parâmetros.
		 * O nome do parâmetro deve ser igual ao inserido no SQL.
		 * A letra "p" antes do nome do parâmetro não é obrigatório, mas 
		 * nos ajuda a identificar que esse nome define um parâmetro.
		 * 
		 */
		Query query = em.createQuery(jpql);
		//query.setParameter("pconta", conta.getId());
		query.setParameter("pconta", conta);
		query.setParameter("ptipomovimentacao", TipoMovimentacao.SAIDA);

		List<Movimentacao> resultados = query.getResultList();

		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
		}

		em.getTransaction().commit();
		em.close();
	}
}
