package br.com.totustuus.financas.main;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.totustuus.financas.model.Conta;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String profissao;
	private String endereco;

	/*
	 * Em nosso caso, uma conta ter� apenas um titular, e isso significa que uma
	 * conta n�o pode pertencer a mais de um cliente. Portanto, o relacionamento
	 * entre Cliente e Conta � de "um para um". Usaremos a anota��o @OneToOne para
	 * represent�-lo.
	 * 
	 * A anota��o @OneToOne por padr�o n�o nos garante essa restri��o, portanto �
	 * necess�rio adicionarmos a anota��o @JoinColumn(unique=true).
	 * 
	 * Para que @JoinColumn funcione, � necess�rio que as tabelas sejam criadas com 
	 * esse comando essa anota��o. Caso contr�rio, nunca teremos um registro 1:1
	 */
	@JoinColumn(unique = true)
	@OneToOne
	private Conta conta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
