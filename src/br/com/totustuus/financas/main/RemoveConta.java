package br.com.totustuus.financas.main;

import javax.persistence.EntityManager;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.util.JPAUtil;

public class RemoveConta {

	/*
	 * Enfatizando que a tarefa do desenvolvedor ao trabalhar com JPA é transformar
	 * o status das entidades em Managed, para conseguir utilizar a sincronização,
	 * já conhecemos o método persist(), capaz de transformar uma entidade Transient
	 * em Managed, e agora aprendemos sobre o método merge(), para uma entidade que
	 * já foi Managed, é Detached, e é transformada em Managed de novo.
	 * 
	 */
	public static void main(String[] args) {

		System.out.println("INICIANDO");

		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
		em.getTransaction().begin();

		/*
		 * Para remover qualquer entidade, ela precisa estar gerenciada (Managed). Então
		 * vamos carregar a conta antes com o método find() deletando-a em seguida.
		 * 
		 * Após a chamada de remove(), o objeto não tem mais representação no banco, já
		 * que foi removido. Porém, ele continua existindo na memória, em um estado
		 * conhecido como Removed.
		 */
		Conta conta = em.find(Conta.class, 5);
		em.remove(conta);

		em.getTransaction().commit();
		em.close();

		System.out.println("FIM");
	}
}
