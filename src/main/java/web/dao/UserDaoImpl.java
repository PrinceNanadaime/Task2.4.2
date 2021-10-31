package web.dao;

import org.springframework.stereotype.Repository;
import web.models.User;

import javax.persistence.*;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    private EntityManager entityManager = Persistence
            .createEntityManagerFactory("per")
            .createEntityManager();
    @Override
    public List<User> index() {
        entityManager.getTransaction().begin();
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public User show(int id) {
        entityManager.getTransaction().begin();
        return entityManager.find(User.class, id);
    }

    @Override
    public void save(User user) {
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(int id, User user) {
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM User u WHERE u.id = :id");
        query.setParameter("id", id);
        entityManager.getTransaction().commit();
        query.executeUpdate();
    }

    @Override
    public User getUserByName(String username) {
        entityManager.getTransaction().begin();
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username=:username",
                User.class).setParameter("username", username);
        entityManager.getTransaction().commit();
        query.executeUpdate();
        return query.getSingleResult();
    }
}

