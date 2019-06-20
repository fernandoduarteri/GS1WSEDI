package persist;


import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;



public class JPAEntity {
	private Logger log = Logger.getLogger(getClass().getName());
	private EntityManager em;
	public EntityManager getEntityManager() {
		if(em==null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicesEDI");
			em = factory.createEntityManager();
		}
		return em;
	}
	
	
	public void guardar(Object object) throws Exception {
		try {
			EntityManager em = this.getEntityManager();
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			em.persist(object);
			tx.commit();
		} catch (Exception e) {
			log.info("Metodo Guardar Exception: " + e);
			throw new Exception(e);
		}
	}
	
	
	public void editar(Object object) throws Exception {
		try {
			EntityManager em = this.getEntityManager();
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			em.merge(object);
			tx.commit();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		} 
	}
	
	public List<?> findAll(String table) throws Exception {
		EntityManager em = this.getEntityManager();
		try {
			return (List<?>) em.createNamedQuery(table + ".findAll").getResultList();
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

}
