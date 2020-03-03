package br.com.totustuus.financas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*
 * Para mapear a classe Conta � preciso anot�-la com @Entity do 
 * pacote javax.persistence. Entidades (ou Entities) s�o as 
 * classes que t�m uma tabela associada.
 * 
 * Al�m disso, o Hibernate vau criar essa tabela como uma entidade do banco.
 */
@Entity
public class Conta {

	/*
	 * � obrigat�rio declarar o atributo que representa a chave prim�ria com @Id.
	 * 
	 * A anota��o @GeneratedValue � opcional, e � muito comum us�-la com chaves
	 * auxiliares. Com ela indicamos que o banco deve atribuir o valor da chave, e
	 * n�o a aplica��o. Ao inserir uma conta no banco, automaticamente ser� alocada
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
