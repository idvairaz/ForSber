package spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {

        entityManager.remove(user);
    }

    @Override
    public User edit(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User getById(int id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User getByUsername(String username) {
        return entityManager.createQuery("SELECT u FROM User AS u JOIN FETCH u.roles WHERE u.username = :username", User.class)
                .setParameter("username" , username).getSingleResult();
    }

}
