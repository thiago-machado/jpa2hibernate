package br.com.totustuus.financas.main;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.model.TipoMovimentacao;
import br.com.totustuus.financas.model.dao.MovimentacaoDAO;
import br.com.totustuus.financas.util.JPAUtil;

public class TesteJPQL4 {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);

		MovimentacaoDAO movimentacao = new MovimentacaoDAO(em);
		List<Double> medias = movimentacao.getMediasPorDiaETipo(TipoMovimentacao.SAIDA, conta);

		for(Double media : medias) {
			System.out.println("Média: " + media);
		}

		em.getTransaction().commit();
		em.close();
	}
}
