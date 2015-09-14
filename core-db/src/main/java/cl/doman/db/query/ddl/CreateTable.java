package cl.doman.db.query.ddl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cl.doman.db.server.PersistenceUnitFactory;

public class CreateTable {
	// TODO: crear y modificar tablass
	static Logger log = LoggerFactory.getLogger(CreateTable.class);

	protected EntityManager getEntityManager() {
		PersistenceUnitFactory server = PersistenceUnitFactory.getInstance();
		return server.getEntityManager();
	}

	public void createTable(String name){
		PersistenceUnitFactory persistenceUnitFactory = PersistenceUnitFactory
				.getInstance();
		EntityManager entityManager = persistenceUnitFactory.getEntityManager();
		try {
			EntityTransaction entityTransaction = entityManager
					.getTransaction();
			entityTransaction.begin();
			Query query = entityManager
					.createNativeQuery("CREATE TABLE `#tablename` (`id` int(11),`Birth_Date` datetime)");
			query.setParameter("tablename", name);
			// cualqueir comando que no requiera de salida
			query.executeUpdate();
			entityTransaction.commit();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} finally {
			entityManager.close();
		}
	}
	
	
	public static void main(String[] args) {

		// http://db.apache.org/ddlutils/api-usage.html#The+model

	}
}
