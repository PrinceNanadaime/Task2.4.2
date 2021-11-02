package web.dao;


import web.models.Role;

import java.util.List;

public interface RoleDao {
    List<Role> getRoles();

    void save(Role role);

    void update(Role role);

    void delete(long id);

    Role getRoleByName(String name);
}
