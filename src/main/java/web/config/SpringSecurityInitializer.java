package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import web.dao.UserDao;
import web.models.Role;
import web.models.User;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    @Autowired
    private User user;

    @Autowired
    private User admin;

    @Autowired
    private UserDao userDao;

    @PostConstruct
    public void addAdminAndUserToDataBase(){
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(new Role(1,"USER"));
        user.setName("Marc");
        user.setLastName("Hudson");
        user.setUsername("marc_hudson");
        user.setPassword("marc1987");
        user.setRoles(userRoles);
        userDao.save(user);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(new Role(2,"ADMIN"));
        admin.setName("Nikita");
        admin.setLastName("Kotenkov");
        admin.setUsername("nikita_kotenkov");
        admin.setPassword("nikita2001");
        admin.setRoles(adminRoles);
        userDao.save(admin);
    }

    @PreDestroy
    public void removeUsersFromDb(){

    }
}