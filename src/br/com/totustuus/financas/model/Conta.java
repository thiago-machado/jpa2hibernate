package br.com.totustuus.financas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Para mapear a classe Conta é preciso anotá-la com @Entity do 
 * pacote javax.persistence. Entidades (ou Entities) são as 
 * classes que têm uma tabela associada.
 * 
 * Além disso, o Hibernate vau criar essa tabela como uma entidade do banco.
 */
@Entity
public class Conta {

	/*
	 * É obrigatório declarar o atributo que representa a chave primária com @Id.
	 * 
	 * A anotação @GeneratedValue é opcional, e é muito comum usá-la com chaves
	 * auxiliares. Com ela indicamos que o banco deve atribuir o valor da chave, e
	 * não a aplicação. Ao inserir uma conta no banco, automaticamente será alocada
	 * uma ID.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titular;
	private String numero;
	private String banco;
	private String agencia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

}
