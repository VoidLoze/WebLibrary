package WebLibrary.WebLibrary.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<E> {

    private final Class<E> entityClass;

    @PersistenceContext
    private EntityManager entityManager;

    public BaseRepository(Class<E> entity) {
        this.entityClass = entity;
    }

    @Transactional
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Transactional
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Transactional
    public Optional<E> findById(int id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    @Transactional
    public List<E> findAll() {
//        return entityManager.createQuery(entityClass.getName(), entityClass).getResultList();
    String jpql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
    return entityManager.createQuery(jpql, entityClass).getResultList();
    }

    protected EntityManager getEntityManger() {
        return entityManager;
    }

    protected Class<E> getEntityClass(){
        return entityClass;
    }

    @Transactional
    public void delete(E entityClass) {
        if (entityClass != null && entityManager.contains(entityClass)) {
            entityManager.remove(entityClass);
        } else {
            entityManager.remove(entityManager.merge(entityClass));
        }
    }

    @Transactional
    public void deleteById(int id) {
        Optional<E> entity = findById(id);
        entity.ifPresent(this::delete);
    }
}