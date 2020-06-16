package demo.vertx.app.dal.repo;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractEntityRepo<E> implements CommonRepo<E> {

    private Class<E> entityType;
    protected String defaultOrder;
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AbstractEntityRepo(Class<E> entityType) {
        this.entityType = entityType;
    }

    public void setDefaultOrder(String defaultOrder) {
        this.defaultOrder = defaultOrder;
    }

    @Override
    public E add(E entity) {
        getCurrentSession().persist(entity);
        return entity;
    }

    @Override
    public E update(E entity) {
        getCurrentSession().update(entity);
        return entity;
    }

    @Override
    public void remove(Integer id) {
        E foundedEntity = getCurrentSession().load(entityType, id);
        getCurrentSession().delete(foundedEntity);
    }

    @Override
    public E findById(Integer id) {
        return getCurrentSession().get(entityType, id);
    }

    @Override
    public List<E> findList() {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<E> entityCriteriaQuery = criteriaBuilder.createQuery(entityType);
        Root<E> from = entityCriteriaQuery.from(entityType);
        entityCriteriaQuery.select(from);
        if (null != defaultOrder) {
            entityCriteriaQuery.orderBy(criteriaBuilder.asc(from.get(defaultOrder)));
        }
        Query<E> entityQuery = getCurrentSession().createQuery(entityCriteriaQuery);
        List<E> entityResultList = entityQuery.getResultList();
        return entityResultList;
    }

    @Override
    public Integer findMaxId() {
        Integer id = 0;
        CriteriaBuilder builder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Integer> employeeCriteriaQuery = builder.createQuery(Integer.class);
        Root<E> root = employeeCriteriaQuery.from(entityType);
        employeeCriteriaQuery.select(builder.max(root.get("id")));
        Query<Integer> createQuery = null;

        if (countEntity() == 0) {
            id = 1;
        } else {
            Query<Integer> employeeQuery = getCurrentSession().createQuery(employeeCriteriaQuery);
            id = employeeQuery.getSingleResult() + 1;
        }

        return id;
    }

    @Override
    public Long countEntity() {
        CriteriaBuilder criteriaBuilder = getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> cirteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<E> root = cirteriaQuery.from(entityType);
        cirteriaQuery.select(criteriaBuilder.count(root));
        Query<Long> countQuery = getCurrentSession().createQuery(cirteriaQuery);
        return countQuery.getSingleResult();
    }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

}
