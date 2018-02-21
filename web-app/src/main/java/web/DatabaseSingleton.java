package web;

import lombok.Getter;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.query.Query;

import java.util.List;
import java.util.ArrayList;

import java.net.URL;

class DatabaseSingleton {
	private static DatabaseSingleton instance = null;
	
	private Session session;
	private SessionFactory sessionFactory;
	private Configuration conf;
	private ServiceRegistry serviceRegistry;
	
	@Getter
	private List<Ranking> rankingList;
		
	public static DatabaseSingleton instance(){
		if ( !(instance instanceof DatabaseSingleton) ) {
			instance = new DatabaseSingleton();
		}
		return DatabaseSingleton.instance;
	}
	
	private DatabaseSingleton(){
		URL configFile = DatabaseSingleton.class.getResource("../configuration/hibernate.cfg.xml");
		System.out.println(configFile);
		this.sessionFactory = new Configuration().configure(configFile).buildSessionFactory();
		
		try {
			this.session = sessionFactory.openSession();
		} catch(Exception sqlException) {
			sqlException.printStackTrace();
		}
		
		rankingList = new ArrayList<>();
	}
	
	public void refreshRanking(){
		String sql = "from web.Ranking";
		Query<Ranking> query = this.session.createQuery(sql);
		query.setCacheable(true);
		this.rankingList = query.list();
	}
}