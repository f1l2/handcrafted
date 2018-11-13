package at.f1l2.lab.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Base repository includes CRUD functionality.
 *
 * @param <T>
 * @author Filz Manuel
 * @date 10.07.2017
 */
public abstract class BaseRepository<T> {

	private Class<T> persistentClass;

	private String basicQueryForAttribute = "SELECT t FROM [CLASS] t WHERE t.[ATTRIBUTE] = :[ATTRIBUTE]";

	private String basicQueryForIsNull = "SELECT t FROM [CLASS] t WHERE t.[ATTRIBUTE] IS NULL";

	@PersistenceContext
	protected EntityManager em;

	public BaseRepository(Class<T> persistentClass) {
		this.setPersistentClass(persistentClass);
		this.basicQueryForAttribute = this.basicQueryForAttribute.replaceAll("\\[CLASS\\]",
				persistentClass.getSimpleName());
		this.basicQueryForIsNull = this.basicQueryForIsNull.replaceAll("\\[CLASS\\]", persistentClass.getSimpleName());
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	/**
	 * Saves and return the saved Entity
	 *
	 * @param entity
	 * @return
	 */
	public T save(T entity) {
		em.persist(entity);
		return entity;
	}

	/**
	 * Persists the a whole list
	 *
	 * @param entityList
	 * @Author Diego Krupitza
	 */
	public void save(List<T> entityList) {
		entityList.forEach(entity -> em.persist(entity));
	}

	public T update(T entity) {
		return em.merge(entity);
	}

	public void delete(T entity) {
		em.remove(entity);
	}

	public void detach(T entity) {
		em.detach(entity);
	}

	public T findById(long id) {
		return (T) em.find(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return em.createQuery("FROM " + persistentClass.getName()).getResultList();
	}

	/**
	 * Method aim to delete all entities of a specific type.
	 * <p>
	 * Use it with caution.
	 */
	public void deleteAll() {
		em.createQuery("DELETE FROM " + persistentClass.getName()).executeUpdate();
	}

	/**
	 * Method returns single item based on a specific attribute. If attribute
	 * matches more than one item, the returned item is determined randomly. As
	 * a consequence, the method should only be used if specified attribute is
	 * unique.
	 */
	public T findSingleByAttribute(String attribute, String value) {
		return findByAttribute(attribute, value).stream().findFirst().orElse(null);
	}

	/**
	 * Method returns a list of items based on a specific attribute.
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByAttribute(String attribute, String value) {
		String query = basicQueryForAttribute.replaceAll("\\[ATTRIBUTE\\]", attribute);
		return em.createQuery(query).setParameter(attribute, value).getResultList();
	}

	/**
	 * Method return single item based on an attribute, which is null.
	 */
	public T findSingleIsNull(String attribute) {
		return findIsNull(attribute).stream().findFirst().orElse(null);
	}

	/**
	 * Method returns a list of items based on an attribute, which is null.
	 */
	@SuppressWarnings("unchecked")
	public List<T> findIsNull(String attribute) {
		String query = basicQueryForIsNull.replaceAll("\\[ATTRIBUTE\\]", attribute);
		return em.createQuery(query).getResultList();
	}

	/**
	 * Method returns the number of items. The underlying SQL statement uses a
	 * COUNT clause.
	 */
	public Long countAll() {

		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(persistentClass)));

		return (em.createQuery(cq).getSingleResult());
	}
}