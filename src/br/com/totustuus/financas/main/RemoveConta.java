package br.com.totustuus.financas.main;

import javax.persistence.EntityManager;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.util.JPAUtil;

public class RemoveConta {

	/*
	 * Enfatizando que a tarefa do desenvolvedor ao trabalhar com JPA � transformar
	 * o status das entidades em Managed, para conseguir utilizar a sincroniza��o,
	 * j� conhecemos o m�todo persist(), capaz de transformar uma entidade Transient
	 * em Managed, e agora aprendemos sobre o m�todo merge(), para uma entidade que
	 * j� foi Managed, � Detached, e � transformada em Managed de novo.
	 * 
	 */
	public static void main(String[] args) {

		System.out.println("INICIANDO");

		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
		em.getTransaction().begin();

		/*
		 * Para remover qualquer entidade, ela precisa estar gerenciada (Managed). Ent�o
		 * vamos carregar a conta antes com o m�todo find() deletando-a em seguida.
		 * 
		 * Ap�s a chamada de remove(), o objeto n�o tem mais representa��o no banco, j�
		 * que foi removido. Por�m, ele continua existindo na mem�ria, em um estado
		 * conhecido como Removed.
		 */
		Conta conta = em.find(Conta.class, 5);
		em.remove(conta);

		em.getTransaction().commit();
		em.close();

		System.out.println("FIM");
	}
}
