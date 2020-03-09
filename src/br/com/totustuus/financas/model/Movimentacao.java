package br.com.totustuus.financas.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.totustuus.financas.main.Categoria;

@Entity
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private BigDecimal valor;

	/*
	 * Como agora o atributo é uma Enum, precisaremos anotá-lo com @Enumerated. Além
	 * disso, queremos mapeá-lo no banco de dados como sendo do tipo String.
	 * Precisaremos então definir o parâmetro EnumType para STRING. Isso significa
	 * que no registro do banco ficará gravado o varchar: SAIDA ou ENTRADA.
	 */
	@Enumerated(EnumType.STRING)
	private TipoMovimentacao tipoMovimentacao;

	/*
	 * No Java, quando trabalhamos com datas, normalmente usamos o tipo Calendar.
	 * Mas como cada banco lida de formas diferentes com dados deste tipo,
	 * precisaremos indicar ao JPA a forma que queremos armazenar a data no banco.
	 * 
	 * Devemos então anotar o atributo com @Temporal, depois definir o parâmetro de
	 * precisão desejado (TemporalType). Aqui, temos três opções:
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
	 * Várias movimentações podem pertencer a uma única conta. Ou seja, a
	 * cardinalidade é de muitos para um. Para indicarmos este tipo de
	 * relacionamento no atributo Conta, precisaremos utilizar @ManyToOne
	 * ("muitas movimentações para uma conta"):
	 */
	@ManyToOne
	private Conta conta;

	/*
	 * Para uma movimentação, podemos ter diversas categorias e portanto,
	 * utilizaremos a anotação @OneToMany.
	 * 
	 * Será que isso faz sentido? Ao criarmos uma movimentação com a categoria
	 * Viagem, apenas ela poderá ter essa categoria? O que queremos é que várias
	 * movimentações possam ter esta categoria. Corrigindo nossa frase, teremos:
	 * 
	 * Para várias movimentações, podemos ter diversas categorias.
	 * 
	 * Conseguiremos esse efeito utilizando a anotação @ManyToMany
	 */
	@ManyToMany
	private List<Categoria> categoria;

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

	public List<Categoria> getCategoria() {
		return categoria;
	}

	public void setCategoria(List<Categoria> categoria) {
		this.categoria = categoria;
	}
	

}
