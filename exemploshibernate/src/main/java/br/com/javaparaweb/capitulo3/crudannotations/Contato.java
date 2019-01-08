package br.com.javaparaweb.capitulo3.crudannotations;

import java.sql.Date;
import javax.persistence.*;

@Entity
@Table(name = "contato")//o parametro name: especifica o nome da tabela dentro do banco de dados
public class Contato {
	
	//para que o mapeamento dessa classe seja reconhecida pelo Hibernate deve ser colocar um comando dentro da uma tag especifica no hibernate.cfg
	//<mapping class="br.com.javaparaweb.capitulo3.crudannotations.Contato"/> 

	@Id
	@GeneratedValue //autonumeração
	@Column(name = "codigo")
	private Integer	codigo;

	@Column(name = "nome", length = 50, nullable = true) //se a coluna não puder receber valores nulo configure essa propriedade como false.
	private String	nome;

	@Column(name = "telefone", length = 50, nullable = true)
	private String	telefone;

	@Column(name = "email", length = 50, nullable = true)
	private String	email;

	@Column(name = "dt_cad", nullable = true)
	private Date		dataCadastro;

	@Column(name = "obs", nullable = true)
	private String	observacao;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

} 
