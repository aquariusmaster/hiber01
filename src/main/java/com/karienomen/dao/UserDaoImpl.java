package com.karienomen.dao;

import com.karienomen.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

/**
 * Created by andreb on 08.02.17.
 */
@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    // An EntityManager will be automatically injected from entityManagerFactory
    // setup on DatabaseConfig class.
    @PersistenceContext
    private EntityManager entityManager;


    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void create(User user) {

        entityManager.persist(user);

    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void delete(User user) {
        if (entityManager.contains(user))
            entityManager.remove(user);
        else
            entityManager.remove(entityManager.merge(user));

    }

    @Override
    public User getByName(String name) {
        return (User) entityManager.createQuery(
                "from User where name = :name")
                .setParameter("name", name)
                .getSingleResult();


    }

    @Override
    public boolean exists(String name) {

        User fetched = getByName(name);

        if (fetched != null ){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<User> getAll() {

        List<User> list = entityManager.createQuery("from User").getResultList();
        return list;
    }

    @Override
    public void deleteAll() {

        List<User> toDelete = getAll();
        logger.info("List to delete: " + toDelete);
        for(User user : toDelete){
            entityManager.remove(user);
        }

    }

    @Override
    public List<PhoneNumber> getPhoneNumberByUserId(long userId) {

        List<PhoneNumber> list = entityManager.createQuery(
                "from PhoneNumber where owner.userId = :userId")
                .setParameter("userId", userId)
                .getResultList();
        return list;
    }

    @Override
    public List<User> searchInEachField(String searchTerm) {

        Query q = createQueryWithPredicate(searchTerm);
        List<User> list = q.getResultList();

        return list;
    }

    private Query createQueryWithPredicate(String searchTerm){

        String containsLikePattern = getContainsLikePattern(searchTerm);

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        Predicate namePredicate = cb.like(root.get(User_.name), containsLikePattern);
        //Create Predicate for Inner object PhoneNumber list
        SetJoin<User, PhoneNumber> setJoin = root.join(User_.phones);
        Predicate phonesPredicate = cb.or(
                cb.like(setJoin.get(PhoneNumber_.code), containsLikePattern),
                cb.like(setJoin.get(PhoneNumber_.number), containsLikePattern));
        //Predicate for Address inner object
        Path<Address> userAddress = root.get(User_.address);
        Predicate addressPredicate = cb.or(
                cb.like(userAddress.get(Address_.country), containsLikePattern),
                cb.like(userAddress.get(Address_.city), containsLikePattern),
                cb.like(userAddress.get(Address_.addressLine), containsLikePattern));

        Predicate mainPredicate = cb.or(namePredicate, phonesPredicate, addressPredicate);

        Query q = entityManager.createQuery(cq.where(mainPredicate));

        return q;
    }

    private static String getContainsLikePattern(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return "%";
        }
        else {
            return "%" + searchTerm + "%";
        }
    }
}
