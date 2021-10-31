package web.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    private EntityManager entityManager = Persistence
            .createEntityManagerFactory("per")
            .createEntityManager();

    @Override
    public List<Role> getRoles() {
        entityManager.getTransaction().begin();
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }

    @Override
    public void save(Role role) {
        entityManager.getTransaction().begin();
        entityManager.persist(role);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(Role role) {
        entityManager.getTransaction().begin();
        entityManager.merge(role);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.find(Role.class, id));
        entityManager.getTransaction().commit();
    }

    @Override
    public Role getRoleByName(String name) {
        entityManager.getTransaction().begin();
        TypedQuery<Role> query = entityManager.createQuery("select u from Role u where u.name=:role",
                Role.class).setParameter("role", name);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        return query.getSingleResult();
    }
}
