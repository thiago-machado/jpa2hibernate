package br.com.totustuus.financas.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

	/*
	 * Criando relacionamento BiDirecional. Agora, atrav�s da movimenta��o chegamos 
	 * na conta e vice versa.
	 * A anota��o indica que uma conta pode ter v�rias movimenta��es
	 * 
	 * (mappedBy="conta") = estamos dizendo que o relacionamento FORTE pertence a movimenta��o.
	 * No caso, Movimentacao possui o atributo conta. � movimenta��o quem cria o relacionamento,
	 * at� por que � Movimenta��o quem possui o campo aluno_id em sua tabela.
	 * 
	 * Como n�s queremos que Conta tenha acesso a suas movimenta��es, basta acrescentarmos 
	 * @OneToMany(mappedBy="conta"), que significa que Movimentacao possui o atributo conta que 
	 * faz o relacionamento entre as duas entidades. Novamente, Movimentacao � o lado FORTE por 
	 * criar o relacionamento.
	 * 
	 * fetch = FetchType.EAGER = ao realizar um SELECT * FROM Conta, todas as movimenta��es 
	 * ser�o selecionadas. � como se fiz�ssemos um SELECT com JOIN em todas as movimenta��es.
	 * As contas sem movimenta��o ter�o uma lista vazia.
	 */
	//@OneToMany(mappedBy="conta", fetch = FetchType.EAGER)
	@OneToMany(mappedBy="conta")
	private List<Movimentacao> movimentacoes;
	
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

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
	

}
