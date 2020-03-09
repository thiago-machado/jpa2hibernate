package br.com.totustuus.financas.main;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.util.JPAUtil;

/*
 * Como importar via Maven
 * 
 * <dependency>
    <groupId>org.hibernate</groupId>
    <artifactId>hibernate-entitymanager</artifactId>
    <version>5.2.8.Final</version>
   </dependency>
 */
public class InsereConta {

	public static void main(String[] args) {

		System.out.println("INICIANDO");

		/*
		 * Sem o método persist(), a conta não seria salva e sumiria completamente caso
		 * a aplicação terminasse.
		 * 
		 * Esse estado é chamado Transiente(ou Transient) e a tarefa do método persist()
		 * é justamente alterar esse estado para Gerenciado(Managed).
		 * 
		 * Tanto é que, após o persist(), se inserirmos um conteúdo em algum atributo
		 * dessa Conta, a mesma seria atualizada.
		 */
		Conta conta = new Conta();
		conta.setTitular("Luís");
		conta.setBanco("Santander");
		conta.setAgencia("12201");
		conta.setNumero("664-0");

		// List<Conta> contas = getContas();

		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();

		// precisa sempre abrir uma transação, fazer o que tem que fazer e commitar
		em.getTransaction().begin();

		/*
		 * for(Conta conta : contas) em.persist(conta);
		 */

		em.persist(conta);

		/*
		 * Após o persist(), a conta passa de Transient para Managed. Ou seja, se ela
		 * sofrer alguma alteração como abaixo, a conta será editada (igual como foi no
		 * find())
		 */
		conta.setBanco("Santander");

		em.getTransaction().commit();

		/*
		 * No fim, fechar factory e manager.
		 * 
		 * Reforçando que o estado Managed só irá durar enquanto não fecharmos o
		 * EntityManager - a partir daí, nenhuma das entidades segue neste estado.
		 * 
		 */
		em.close();
		//JPAUtil.getEntityManagerFactoty().close();

		atualizarContaDetached(conta);

		System.out.println("FIM");

	}

	/**
	 * Imagine que busquemos uma conta, fechando a entidade em seguida, após o
	 * término de uma requisição, por exemplo.
	 * 
	 * Abriremos outro EntityManager e alteramos a conta.
	 * 
	 * Queremos que esta conta seja sincronizada - mas como transformaremos algo em
	 * Managed?
	 * 
	 * Teremos que transformar esta conta em Managed para que a sincronização ocorra
	 * de forma automática.
	 * 
	 * Neste momento, qual o estado desta conta? Será que é Transient? Utilizamos o
	 * find pelo "id 1", portanto sabemos que a conta tem este ID. Ela continua
	 * existindo no banco? Sim! 
	 * 
	 * Porém, ela não é Transient, pois sua característica
	 * é justamente nunca ter sido gerenciada pelo banco.
	 * 
	 * Significa que quando "matamos" a aplicação, a entidade "morre" junto. Esta
	 * entidade, então, é Managed? Não, pois o EntityManager que buscou esta
	 * entidade já foi fechado. Qual é o estado em que ela se encontra, então?
	 * 
	 * Trata-se do estado Detached, em que a conta não é mais gerenciada pelo JPA.
	 * Há uma representação sua no banco, no entanto a sincronização automática não
	 * está ativada, pois o gerenciamento não ocorre mais. Nosso objetivo, então, é
	 * transformar esta conta de Detached para Managed para que a sincronização
	 * aconteça.
	 * 
	 * @param conta
	 */
	private static void atualizarContaDetached(Conta conta) {

		System.out.println("ATUALIZANDO REGISTRO DETACHED");
		
		EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
		em.getTransaction().begin();
		
		conta.setBanco("Inter");
		
		// O método merge() transforma um registro que estava Detached em Managed novamente
		em.merge(conta);
		
		
		em.getTransaction().commit();
		em.close();
		//JPAUtil.getEntityManagerFactoty().close();
		
		System.out.println("FINALIZANDO ATUALIZAÇÃO");
	}

	/**
	 * Retorna uma lista de contas
	 * 
	 * @return
	 */
	private static List<Conta> getContas() {

		List<Conta> contas = new ArrayList<Conta>();

		Conta conta1 = new Conta();
		Conta conta2 = new Conta();
		Conta conta3 = new Conta();
		Conta conta4 = new Conta();
		Conta conta5 = new Conta();

		conta1.setBanco("001 - BANCO DO BRASIL");
		conta1.setNumero("16987-8");
		conta1.setAgencia("6543");
		conta1.setTitular("Maria dos Santos");

		conta2.setBanco("237 - BANCO BRADESCO");
		conta2.setNumero("86759-1");
		conta2.setAgencia("1745");
		conta2.setTitular("Paulo Roberto Souza");

		conta3.setBanco("341 - BANCO ITAU UNIBANCO");
		conta3.setNumero("46346-3");
		conta3.setAgencia("4606");
		conta3.setTitular("Antonio Duraes");

		conta4.setBanco("033 - BANCO SANTANDER");
		conta4.setNumero("12345-6");
		conta4.setAgencia("9876");
		conta4.setTitular("Leandra Marques");

		conta5.setBanco("104 - CAIXA ECONOMICA FEDERAL");
		conta5.setNumero("98654-3");
		conta5.setAgencia("1234");
		conta5.setTitular("Alexandre Duarte");

		contas.add(conta1);
		contas.add(conta2);
		contas.add(conta3);
		contas.add(conta4);
		contas.add(conta5);

		return contas;
	}
}
