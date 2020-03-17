package br.com.totustuus.financas.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

	/*
	 * Criando relacionamento BiDirecional. Agora, através da movimentação chegamos 
	 * na conta e vice versa.
	 * A anotação indica que uma conta pode ter várias movimentações
	 * 
	 * (mappedBy="conta") = estamos dizendo que o relacionamento FORTE pertence a movimentação.
	 * No caso, Movimentacao possui o atributo conta. É movimentação quem cria o relacionamento,
	 * até por que é Movimentação quem possui o campo aluno_id em sua tabela.
	 * 
	 * Como nós queremos que Conta tenha acesso a suas movimentações, basta acrescentarmos 
	 * @OneToMany(mappedBy="conta"), que significa que Movimentacao possui o atributo conta que 
	 * faz o relacionamento entre as duas entidades. Novamente, Movimentacao é o lado FORTE por 
	 * criar o relacionamento.
	 * 
	 * fetch = FetchType.EAGER = ao realizar um SELECT * FROM Conta, todas as movimentações 
	 * serão selecionadas. É como se fizéssemos um SELECT com JOIN em todas as movimentações.
	 * As contas sem movimentação terão uma lista vazia.
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
