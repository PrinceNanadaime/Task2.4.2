package web.service;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.RoleDao;
import web.models.Role;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{

    private final RoleDao roleDao;

    @Autowired
    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Transactional
    @Override
    public List<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Transactional
    @Override
    public void save(Role role) {
    roleDao.save(role);
    }

    @Transactional
    @Override
    public void update(Role role) {
    roleDao.update(role);
    }

    @Transactional
    @Override
    public void delete(int id) {
    roleDao.delete(id);
    }

    @Transactional
    @Override
    public Role getRoleByName(String name) {
        return roleDao.getRoleByName(name);
    }
}
