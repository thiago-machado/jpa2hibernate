package br.com.totustuus.financas.main;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.totustuus.financas.model.Categoria;
import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.model.Movimentacao;
import br.com.totustuus.financas.model.TipoMovimentacao;
import br.com.totustuus.financas.util.JPAUtil;

public class TesteMovimentacoesComCategoria {

	public static void main(String[] args) {
		
		Categoria categoria1 = new Categoria("Viagem");
        Categoria categoria2 = new Categoria("Negócios");

        Conta conta = new Conta();
        conta.setId(2); 

        Movimentacao movimentacao1 = new Movimentacao();
        movimentacao1.setData(Calendar.getInstance()); // hoje
        movimentacao1.setDescricao("Viagem à SP");
        movimentacao1.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao1.setValor(new BigDecimal("100.0"));
        movimentacao1.setCategoria(Arrays.asList(categoria1, categoria2));

        movimentacao1.setConta(conta);

        Movimentacao movimentacao2 = new Movimentacao();
        movimentacao2.setData(Calendar.getInstance()); // hoje
        movimentacao2.setDescricao("Viagem ao RJ");
        movimentacao2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
        movimentacao2.setValor(new BigDecimal("300.0"));
        movimentacao2.setCategoria(Arrays.asList(categoria1, categoria2));

        movimentacao2.setConta(conta);

        EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
        em.getTransaction().begin();

        // primeiro cadastra as categorias
        em.persist(categoria1);
        em.persist(categoria2);
        
        // depois cadastra a movimentacao
        em.persist(movimentacao1);
        em.persist(movimentacao2);

        em.getTransaction().commit();    
        em.close(); 
	}
}
