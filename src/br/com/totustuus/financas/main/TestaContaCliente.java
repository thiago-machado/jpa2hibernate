package br.com.totustuus.financas.main;

import javax.persistence.EntityManager;

import br.com.totustuus.financas.model.Cliente;
import br.com.totustuus.financas.model.Conta;
import br.com.totustuus.financas.util.JPAUtil;

public class TestaContaCliente {

	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
        cliente.setNome("Leonardo");
        cliente.setEndereco("Rua Fulano, 123");
        cliente.setProfissao("Professor");
        
        Cliente cliente2 = new Cliente();
        cliente2.setNome("Douglas");
        cliente2.setEndereco("Rua Fulano, 234");
        cliente2.setProfissao("Professor");

        Conta conta = new Conta();
        conta.setId(2);

        cliente.setConta(conta);
        cliente2.setConta(conta);

        EntityManager em = JPAUtil.getEntityManagerFactoty().createEntityManager();
        em.getTransaction().begin();

        em.persist(cliente);
        //em.persist(cliente2);
        // esse registro não será inserido pois não podemos ter dois clientes para uma mesma conta

        em.getTransaction().commit();  
        
	}
}
