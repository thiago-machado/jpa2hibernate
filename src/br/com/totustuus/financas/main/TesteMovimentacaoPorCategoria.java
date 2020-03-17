package br.com.totustuus.financas.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.totustuus.financas.model.Categoria;
import br.com.totustuus.financas.model.Movimentacao;
import br.com.totustuus.financas.util.JPAUtil;

public class TesteMovimentacaoPorCategoria {

	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
        em.getTransaction().begin();

        Categoria categoria = new Categoria();
        categoria.setId(1);

        /*
         * Fazendo um SELECT entre tabelas ManyToMany.
         * 
         * No caso, queremos buscar todas as movimentações que pertencem a uma determinada categoria.
         * Para fazermos essa seleção, basta fazermos um JOIN com o atributo m.categoria 
         * (que pertence a Movimentacao).
         * 
         * WHERE c = :pcategoria = o JPA Hibernate entende que esse comando significa que 
         * queremos fazer uma seleção usando o ID da categoria.
         */
        String jpql = "SELECT m FROM Movimentacao m JOIN m.categoria c WHERE c = :pcategoria";

        Query query = em.createQuery(jpql);
        query.setParameter("pcategoria", categoria);

        List<Movimentacao> resultados = query.getResultList();

		for (Movimentacao movimentacao : resultados) {
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("Conta.id: " + movimentacao.getConta().getId());
		}

		em.getTransaction().commit();
		em.close();
	
	}

}
