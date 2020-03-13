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
	 * Em nosso caso, uma conta terá apenas um titular, e isso significa que uma
	 * conta não pode pertencer a mais de um cliente. Portanto, o relacionamento
	 * entre Cliente e Conta é de "um para um". Usaremos a anotação @OneToOne para
	 * representá-lo.
	 * 
	 * A anotação @OneToOne por padrão não nos garante essa restrição, portanto é
	 * necessário adicionarmos a anotação @JoinColumn(unique=true).
	 * 
	 * Para que @JoinColumn funcione, é necessário que as tabelas sejam criadas com 
	 * esse comando essa anotação. Caso contrário, nunca teremos um registro 1:1
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
