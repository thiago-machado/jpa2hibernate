package br.com.totustuus.financas.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("financas");
	
	public static EntityManagerFactory getEntityManagerFactoty() {
		return emf;
	}
	
}
