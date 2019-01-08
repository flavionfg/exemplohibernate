package br.com.javaparaweb.capitulo3.conexao;

import org.hibernate.Session;

public class ConectaHibernateMySQL {

	public static void main(String[] args) {
		Session sessao = null;
		sessao = HibernateUtil.getSessionFactory().openSession();
		System.out.println("Conectou!");
		sessao.close();
		//no pom.xml eu mudei a versao do conector do mysql pois não pude encontrar o a versao do livro
		// atualizado para <version>5.1.47</version>.
	}
}
