package persist;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public abstract class JPAEntity<T> {
	private Class<T> entityClass;

	private Logger log = Logger.getLogger(getClass().getName());
	public EntityManager em;

	public JPAEntity(Class<T> entityClass) {
		this.entityClass = entityClass;
		this.getEntityManager();
	}

	public EntityManager getEntityManager() {
		if (em == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicesEDI");
			em = factory.createEntityManager();
		}
		return em;
	}

	public void create(T entity) throws Exception {
		try {
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			tx.commit();
		} catch (Exception e) {
			log.info("Metodo create Exception: " + e);
			throw new Exception(e.getMessage());
		}
	}

	public void edit(T entity) throws Exception {
		try {

			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			em.merge(entity);
			tx.commit();
		} catch (Exception e) {
			log.info("Metodo edit Exception: " + e);
			throw new Exception(e.getMessage());
		}
	}

	public void remove(T entity) throws Exception {
		try {
			em.remove(em.merge(entity));
		} catch (Exception e) {
			log.info("Metodo remove Exception: " + e);
			throw new Exception(e.getMessage());
		}
	}

	public T find(Object id) throws Exception {
		try {
			return em.find(entityClass, id);
		} catch (Exception e) {
			log.info("Metodo find Exception: " + e);
			throw new Exception(e.getMessage());
		}
	}

	public List<T> findAllSorting(String query) throws Exception {
		List<T> getList = new ArrayList<T>();
		try {

			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			TypedQuery<T> q = em.createQuery(query, entityClass);
			getList = (List<T>) q.getResultList();
		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		} 
		return getList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() throws Exception {
		List<T> getList = new ArrayList<T>();
		try {
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			getList = em.createQuery(cq).getResultList();

		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		}
		return getList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] range) throws Exception {
		javax.persistence.Query q;
		try {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0] + 1);
		q.setFirstResult(range[0]);
		}catch(Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		}
		
		return q.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() throws Exception {
		int cuenta = 0;
		try {
			
			javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
			cq.select(em.getCriteriaBuilder().count(rt));
			javax.persistence.Query q = em.createQuery(cq);
			cuenta = ((Long) q.getSingleResult()).intValue();
		} catch (Exception e) {
			log.info("Metodo count Exception: " + e);
			throw new Exception(e.getMessage());
		}
		return cuenta;
	}

//	public void guardar(Object object) throws Exception {
//		try {
//			EntityManager em = this.getEntityManager();
//			EntityTransaction tx;
//			tx = em.getTransaction();
//			tx.begin();
//			em.persist(object);
//			tx.commit();
//		} catch (Exception e) {
//			log.info("Metodo Guardar Exception: " + e);
//			throw new Exception(e);
//		}
//	}
//	
//	
//	public void editar(Object object) throws Exception {
//		try {
//			EntityManager em = this.getEntityManager();
//			EntityTransaction tx;
//			tx = em.getTransaction();
//			tx.begin();
//			em.merge(object);
//			tx.commit();
//		} catch (Exception e) {
//			log.info("Metodo Editar Exception: " + e);
//			throw new Exception(e.getMessage());
//		} 
//	}
//	
//	public List<?> findAll(String table) throws Exception {
//		EntityManager em = this.getEntityManager();
//		try {
//			return (List<?>) em.createNamedQuery(table + ".findAll").getResultList();
//		}catch(Exception e) {
//			log.info("Metodo Listar Exception: " + e);
//			throw new Exception(e.getMessage());
//		}
//	}
//	
//	public void getOne(String table, int id) throws Exception{
//		EntityManager em = this.getEntityManager();
//		try {
//			em.createNamedQuery(table + ".findOne" ,1);
//		}catch (Exception e){
//			log.info("Metodo getOne Exception: " + e);
//			throw new Exception(e.getMessage());
//		}
//	}

}
