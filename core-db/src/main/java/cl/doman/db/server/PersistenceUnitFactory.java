package cl.doman.db.server;

import java.sql.Connection;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class PersistenceUnitFactory {
	static Logger log = LoggerFactory.getLogger(PersistenceUnitFactory.class);
	private static final String PERSISTENCE_UNIT = "principal-unit";
	private EntityManagerFactory entityManagerFactory;
	
	static public PersistenceUnitFactory _instance;
	
	static public void init(Properties properties){
		_instance = new PersistenceUnitFactory(properties);
	}
	
	static public PersistenceUnitFactory getInstance(){
		return _instance;
	}
	
	private PersistenceUnitFactory(Properties properties){
		entityManagerFactory = 
				Persistence.createEntityManagerFactory( PERSISTENCE_UNIT, properties);
		
	}
	
//	//begin borrar
//	private PersistenceUnitFactory(){
//		//_instance = new PersistenceUnitFactory();
//		
//	}
//	
//	public static void set(EntityManagerFactory entityManagerFactory){
//		_instance = new PersistenceUnitFactory();
//		_instance.entityManagerFactory = entityManagerFactory; 
//	}
//	//end borrar
	public EntityManagerFactory getEntityManagerFactory(){
		return entityManagerFactory;
	}
	
	public EntityManager getEntityManager(){
		return entityManagerFactory.createEntityManager();
	}
}
