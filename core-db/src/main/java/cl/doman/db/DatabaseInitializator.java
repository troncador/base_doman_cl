package cl.doman.db;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.commons.configuration.XMLConfiguration;

import cl.doman.base.initializator.BaseSystemInitializator;
import cl.doman.base.initializator.SystemInitializator;
import cl.doman.base.initializator.SystemInitializatorException;
import cl.doman.db.query.dao.EntityDaoCriteria;
import cl.doman.db.server.PersistenceUnitFactory;

public class DatabaseInitializator implements SystemInitializator{
	private EntityDaoCriteria<?, ?> entityDaoCriteria ;
	private Class<? extends EntityDaoCriteria<?, ?>> entityDaoCriteriaClass;
	
	public DatabaseInitializator(Class<? extends EntityDaoCriteria<?, ?>> entityDaoCriteriaClass){
		this.entityDaoCriteriaClass = entityDaoCriteriaClass;
	}
	
	public DatabaseInitializator( EntityDaoCriteria<?, ?> entityDaoCriteria){
		this.entityDaoCriteria = entityDaoCriteria;
	}
	
	public void init(XMLConfiguration globalConfiguration) throws SystemInitializatorException {
		try {
			Properties properties = new Properties();
			
			properties.put("javax.persistence.jdbc.user", 
					globalConfiguration.getString("database.user"));
			properties.put("javax.persistence.jdbc.password",  
					globalConfiguration.getString("database.pass"));
			properties.put("javax.persistence.jdbc.url", 
					globalConfiguration.getString("database.url"));
			properties.put("javax.persistence.jdbc.driver", 
					globalConfiguration.getString("database.driver"));
			
			PersistenceUnitFactory.init(properties);
			
			testSimpleQuery();
		} catch (Exception e) {
			throw new SystemInitializatorException(e.getMessage(),e);
		} 
	}

	public void testSimpleQuery() throws QueryException, InstantiationException, IllegalAccessException{
		if(entityDaoCriteria==null){
			entityDaoCriteria = entityDaoCriteriaClass.newInstance();
		}
			entityDaoCriteria.count();
	}

	public String getName() {
		return "Database";
	}
}
