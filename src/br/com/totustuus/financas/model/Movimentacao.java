package br.com.totustuus.financas.model;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private BigDecimal valor;

	/*
	 * Como agora o atributo � uma Enum, precisaremos anot�-lo com @Enumerated. Al�m
	 * disso, queremos mape�-lo no banco de dados como sendo do tipo String.
	 * Precisaremos ent�o definir o par�metro EnumType para STRING. Isso significa
	 * que no registro do banco ficar� gravado o varchar: SAIDA ou ENTRADA.
	 */
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;

	/*
	 * No Java, quando trabalhamos com datas, normalmente usamos o tipo Calendar.
	 * Mas como cada banco lida de formas diferentes com dados deste tipo,
	 * precisaremos indicar ao JPA a forma que queremos armazenar a data no banco.
	 * 
	 * Devemos ent�o anotar o atributo com @Temporal, depois definir o par�metro de
	 * precis�o desejado (TemporalType). Aqui, temos tr�s op��es:
	 * 
	 * DATE: somente a data, sem a hora;
	 * TIME: somente a hora, sem data;
	 * TIMESTAMP: tanto data quanto hora.
	 * 
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar data;

	private String descricao;

	/*
	 * V�rias movimenta��es podem pertencer a uma �nica conta. 
	 * Ou seja, a cardinalidade � de muitos para um. 
	 * Para indicarmos este tipo de relacionamento no atributo Conta, 
	 * precisaremos utilizar @ManyToOne ("muitas movimenta��es para uma conta"):
	 */
	@ManyToOne
	private Conta conta;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoMovimentacao getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacao tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

}
