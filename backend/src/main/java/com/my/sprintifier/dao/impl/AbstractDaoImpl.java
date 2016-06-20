package com.my.sprintifier.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.PersistenceException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;

import com.my.sprintifier.model.base.AbstractBaseEntity;

public class AbstractDaoImpl<T extends AbstractBaseEntity> {

	protected static final String EMPTY_STRING = "";

	protected static final String INFO = "info";

	protected static final String NAME = "name";

	protected static final int MANAGE_SCOPE_STORE = 2;

	protected static final int MANAGE_SCOPE_LOADED = 32;

	protected static final String USER_FIELD = "user";

	/** The Constant ID. */
	protected static final String ID = "id";

	/** The session factory. */
	@Autowired
	protected SessionFactory sessionFactory;

	/** The clazz. */
	@SuppressWarnings("rawtypes")
	private final Class clazz;


	

	/**
	 * Instantiates a new abstract dao.
	 *
	 * @param clazz
	 *            the clazz
	 */
	@SuppressWarnings("rawtypes")
	public AbstractDaoImpl(Class clazz) {
		this.clazz = clazz;
	}


	public Long addRecord(T entity) {
		try {
			return (Long) sessionFactory.getCurrentSession().save(entity);
		} catch (ConstraintViolationException ex) {
			throw ex;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public void persist(T entity) throws PersistenceException {
		try {
			getSession().persist(entity);
		} catch (ConstraintViolationException ex) {
			throw ex;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public void addRecords(List<T> entities) throws PersistenceException {
		try {
			for (T t : entities) {
				sessionFactory.getCurrentSession().save(t);
			}
		} catch (ConstraintViolationException ex) {
			throw ex;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void deleteRecord(Long id) throws PersistenceException {
		try {
			T entity = (T) getSession().load(clazz, id);
			getSession().delete(entity);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public void updateRecord(T entity) throws PersistenceException {
		try {
			getSession().update(entity);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public Long saveOrUpdate(T entity) throws PersistenceException {
		try {
			getSession().saveOrUpdate(entity);
			return entity.getId();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public T getRecordById(Long id) throws PersistenceException {

		if (id == null) {
			return null;
		}

		try {
			Criteria criteria = createCriteria();
			criteria.add(Restrictions.eq(ID, id));
			
			T t = (T) criteria.uniqueResult();
			
			return t;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> getRecordsByIds(Set<Long> ids) throws PersistenceException {
		try {
			Criteria criteria = createCriteriaForUser();
			if (!ids.isEmpty()) {
				criteria.add(Restrictions.in(ID, ids));
				return criteria.list();
			} else {
				return new ArrayList<T>();
			}
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public List<T> getAll() throws PersistenceException {
		try {
			return queryRecordsList(createCriteria());
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public List<T> getRecordsPage(Integer startPage, Integer pageSize)
			throws PersistenceException {
		try {
			Criteria cr = createCriteria();
			cr.setFirstResult((startPage - 1) * pageSize);
			cr.setMaxResults(pageSize);
			return queryRecordsList(cr);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}

	}

	public Integer count() {
		try {
			Long rowCount = (Long) createCriteria().setProjection(
					Projections.rowCount()).uniqueResult();
			return rowCount.intValue();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public void merge(T entity) {
		getSession().merge(entity);
	}

	public void detachObject(T entity) {
		getSession().evict(entity);
	}

	public void closeSession() {
		getSession().close();
	}

	public void clearSession() {
		getSession().clear();
	}
	
	/**
	 * Gets the session.
	 *
	 * @return the session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * Creates the criteria.
	 *
	 * @param classForCriteria
	 *            the class for criteria
	 * @return the criteria
	 */
	protected Criteria createCriteria(Class<?>... classForCriteria) {

		Class<?> realClass = classForCriteria == null
				|| classForCriteria.length == 0 ? clazz : classForCriteria[0];

		return this.sessionFactory.getCurrentSession()
				.createCriteria(realClass);
	}

	/**
	 * Creates the criteria.
	 *
	 * @param classForCriteria
	 *            the class for criteria
	 * @return the criteria
	 */
	protected Criteria createCriteriaForUser(Class<?>... classForCriteria) {

		Class<?> realClass = classForCriteria == null
				|| classForCriteria.length == 0 ? clazz : classForCriteria[0];
		
		Criteria criteria = createCriteria(realClass);

		return criteria;
	}

	/**
	 * Adds the eq criterion.
	 *
	 * @param criteria
	 *            the criteria
	 * @param field
	 *            the field
	 * @param value
	 *            the value
	 * @return the criteria
	 */
	protected Criteria addEqCriterion(Criteria criteria, final String field,
			final Object value) {

		return criteria.add(Restrictions.eq(field, value));
	}

	/**
	 * Query unique record.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the t
	 */
	@SuppressWarnings("unchecked")
	protected T queryUniqueRecord(Criteria criteria) {
		T record = (T) criteria.uniqueResult();
		clearSession();
		return record;
	}

	/**
	 * Query records list.
	 *
	 * @param criteria
	 *            the criteria
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	protected List<T> queryRecordsList(Criteria criteria) {
		List<T> recordsList = criteria.list();
		clearSession();
		return recordsList;
	}

	public void updateRecords(List<T> entities) throws PersistenceException {
		try {
			for (T t : entities) {
				sessionFactory.getCurrentSession().update(t);
			}
		} catch (ConstraintViolationException ex) {
			throw ex;
		} catch (Exception e) {
			throw new PersistenceException(e);
		}

	}
	
    public void evict(T t) {
        getSession().evict(t);
    }
	
	public void flush() {
		getSession().flush();
	}


	public void deleteRecordByObject(T entity) throws PersistenceException {
		try {
			getSession().delete(entity);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}


	@SuppressWarnings("unchecked")
	public List<T> getShortList(String fieldName, String joinFieldName, Long joinFieldId) {
		Criteria criteria = createCriteria();
		
		if(joinFieldName != null && joinFieldId != null) {
			criteria.createAlias(joinFieldName, "joinField", JoinType.LEFT_OUTER_JOIN);
			criteria.add(Restrictions.eq("joinField.id", joinFieldId));
		}
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.property(ID), ID);
		projectionList.add(Projections.property(fieldName), NAME);
		
		criteria.setProjection(projectionList);
		criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

		return ((List<T>)criteria.list());
	}
	
	protected Criteria addFalseOrNull(Criteria criteria, String property) {
		
		criteria.add(
				Restrictions.or(
						Restrictions.eq(property, false),
						Restrictions.isNull(property)
						)
				);
		
		return criteria;
	}
}
