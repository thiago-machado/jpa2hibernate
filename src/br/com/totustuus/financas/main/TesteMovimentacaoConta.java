package br.com.totustuus.financas.main;

import javax.persistence.EntityManager;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.model.Movimentacao;
import br.com.totustuus.financas.util.JPAUtil;

public class TesteMovimentacaoConta {

	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
        em.getTransaction().begin();

        Movimentacao movimentacao = em.find(Movimentacao.class, 2);
        Conta conta = movimentacao.getConta();

        System.out.println(conta.getTitular()); 
        
        for(Movimentacao m : conta.getMovimentacoes())
        	System.out.println("Movimentação: " + m.getDescricao() + " - " + m.getValor());
        	
        em.getTransaction().commit();
        em.close();
        
	}
}
