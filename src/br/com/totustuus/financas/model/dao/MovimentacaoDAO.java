package br.com.totustuus.financas.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.model.TipoMovimentacao;

public class MovimentacaoDAO {

	private EntityManager em;

	public MovimentacaoDAO(EntityManager em) {
		this.em = em;
	}
	
    public List<Double> getMediasPorDiaETipo(TipoMovimentacao tipoMovimentacao, Conta conta) {
        
    	String jpql = "SELECT DISTINCT AVG(m.valor) "
    			+ "FROM Movimentacao m "
    			+ "WHERE m.conta = :pConta "
    			+ "AND m.tipoMovimentacao = :pTipoMovimentacao "
    			+ "GROUP BY DAY(m.data) ";

        TypedQuery<Double> query = this.em.createQuery(jpql, Double.class);
        query.setParameter("pConta", conta);
        query.setParameter("pTipoMovimentacao", tipoMovimentacao);

        return query.getResultList();
    }
    
}
