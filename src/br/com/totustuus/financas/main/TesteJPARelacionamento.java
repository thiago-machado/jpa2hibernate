package br.com.totustuus.financas.main;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.model.Movimentacao;
import br.com.totustuus.financas.model.TipoMovimentacao;
import br.com.totustuus.financas.util.JPAUtil;

public class TesteJPARelacionamento {

	public static void main(String[] args) {
		
		Conta conta = new Conta();
	    conta.setAgencia("0102");
	    conta.setBanco("Ita�");
	    conta.setNumero("6415-1");
	    conta.setTitular("Mois�s");

	    Movimentacao movimentacao = new Movimentacao();
	    movimentacao.setData(Calendar.getInstance());
	    movimentacao.setDescricao("Churrascaria");
	    movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
	    movimentacao.setValor(new BigDecimal("201.53"));

	    movimentacao.setConta(conta);

	    EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
	    em.getTransaction().begin();

	    /*
	     * A conta precisa ser Managed para conseguirmos pesistir em Movimenta��o.
	     * Nesse caso, como a conta n�o existia, primeiro devemos persist�-la para 
	     * depois inserirmos a movimenta��o.
	     * 
	     * SEMPRE QUE QUEREMOS FAZER UMA INSER��O EM UMA TABELA QUE PRECISA DE UM REGISTRO 
	     * DE CHAVE ESTRANGEIRA, ESSE REGISTRO DA CHAVE ESTRANGEIRA DEVE EXISTIR ANTES E 
	     * SEU ESTADO DEVE SER MANAGED.
	     */
	    em.persist(conta);
	    
	    /*
	     * Assim tamb�m funciona:
	     * 
	     * conta.setId(1); // id de alguma entidade "conta" existente na base de dados
	     * 
	     * movimentacao.setConta(conta);
	     * manager.persist(movimentacao);
	     */
	    
	    em.persist(movimentacao);

	    em.getTransaction().commit();
	    em.close();
	    
	}
}
