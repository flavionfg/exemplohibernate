package br.com.javaparaweb.capitulo3.conexao;

import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	private static final SessionFactory	sessionFactory	= buildSessionFactory();
	
	private static SessionFactory buildSessionFactory() {
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");

			StandardServiceRegistryBuilder registradorServico = new StandardServiceRegistryBuilder();
			registradorServico.applySettings(cfg.getProperties());
			StandardServiceRegistry servico = registradorServico.build();

			return cfg.buildSessionFactory(servico);
	} catch (Throwable e) {
			System.out.println("Cria��o inicial do objeto SessionFactory falhou. Erro: " + e);
			throw new ExceptionInInitializerError(e);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
		
		//Quando o metodo getSessionFactory() for chamado ele retorna a propriedade sessionFactory, esta sempre estar� criada
		//pois pelo fato de ser uma propriedade estatica,� alimentada no exato momento em que a classe � carregada na mem�ria.
	}
}
