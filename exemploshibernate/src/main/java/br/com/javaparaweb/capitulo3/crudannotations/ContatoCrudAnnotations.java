package br.com.javaparaweb.capitulo3.crudannotations;

import java.sql.Date;
import java.util.List;
import org.hibernate.*;
import br.com.javaparaweb.capitulo3.conexao.HibernateUtil;

public class ContatoCrudAnnotations {
	
	// com o Hibernate o Crud não é preciso escrever codigo SQL no codigo-fonte.
	
	private Session sessao;
	public ContatoCrudAnnotations(Session sessao) {
		this.sessao = sessao;
	}
	public void salvar(Contato contato) {
		sessao.save(contato); 
	}
	public void atualizar(Contato contato) {
		sessao.update(contato); 
	}
	public void excluir(Contato contato) {
		sessao.delete(contato); 
	}
	public List<Contato> listar() { 
		// A classe Query monta consulta no padrao HQL(Linguagem Sql própria do Hibernate), é o usado from Contato em vez de 
		//select * from contato pois estamos lidando com objetos e não com linhas resultantes de uma consulta, Contato e from Contato
		//referece a nossa classe e não a tabela no banco de dados, o metodo list() retorna uma lista de objetos consultados.
		Query consulta = sessao.createQuery("from Contato");
		return consulta.list();
	}
	public Contato buscaContato(int valor) {
		Query consulta = sessao.createQuery("from Contato where codigo = :parametro"); 
		consulta.setInteger("parametro", valor); 
		return (Contato) consulta.uniqueResult(); //uniqueResult é um metodo para retornar um unico objeto,que é esperado para uma consulta realizada por chave primaria.

	}
	public static void main(String[] args) {
		Session sessao = HibernateUtil.getSessionFactory().openSession(); //A partir de uma instância de SessionFactory para usarmos o metodo OpenSesssion que abre uma sessão do banco
		Transaction transacao = sessao.beginTransaction(); //O método beginTransaction é que vai garantir que toda as operações realizadas sejam efetivadas no banco apenas se não houver erros

		ContatoCrudAnnotations contatoCrud = new ContatoCrudAnnotations(sessao);
			
		Contato contato1 = new Contato();
		
		//Testando o método Salvar
		contato1.setNome("Solanu");
		contato1.setTelefone("(47) 3333-4444");
		contato1.setEmail("solanu@javaparaweb.com.br");
		contato1.setDataCadastro(new Date(System.currentTimeMillis()));
		contato1.setObservacao("Novo cliente");
		contatoCrud.salvar(contato1); 

		//Testando o método de Atualizar
		contato1.setObservacao("Retomar contato");
		contatoCrud.atualizar(contato1); 
			
		Contato contato2 = new Contato();
		
		contato2.setNome("Lunare");
		contato2.setTelefone("(47) 7777-5555");
		contato2.setEmail("lunare@javaparaweb.com.br");
		contato2.setDataCadastro(new Date(System.currentTimeMillis()));
		contato2.setObservacao("Cliente em dia");
		contatoCrud.salvar(contato2); 

		System.out.println("Total de registros cadastrados: " + contatoCrud.listar().size()); 

		//Testando o método de Excluir
		contatoCrud.excluir(contato2); 

		transacao.commit(); //instrução de commit no banco,confirmando a transação.
			
		System.out.println("Total de registros cadastrados: " + contatoCrud.listar().size()); 
	}
	
	//org.hibernate.Query - Classe responsavel por executar instruções HQL e SQL nativas, realiza consultas parametrizadas e retornar resultados
	//é utilizando-se objeto Session
	
	//org.hibernate.Session - Ela encapsula uma conexão JDBC, além de ser uma fábrica para gerar objetos Transaction.

}
