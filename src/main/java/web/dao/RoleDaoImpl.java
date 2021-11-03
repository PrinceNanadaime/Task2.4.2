package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("select u from Role u", Role.class).getResultList();
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }


    @Override
    public Role getRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery("select u from Role u where u.name=:role",
                Role.class).setParameter("role", name);
        query.executeUpdate();
        return query.getSingleResult();
    }

    @Override
    public Role getRoleByID(long id) {
        TypedQuery<Role> query = entityManager.createQuery("select u from Role u where u.id=:id",
                Role.class).setParameter("id", id);
        query.executeUpdate();
        return query.getSingleResult();
    }
}
