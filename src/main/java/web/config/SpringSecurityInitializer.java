package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import web.models.Role;
import web.models.User;
import web.service.RoleService;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Configuration
@ComponentScan("web")
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    private final User user;

    private final User admin;

    private final UserService userService;

    private final RoleService roleService;

    public SpringSecurityInitializer(User user, User admin, UserService userService, RoleService roleService) {
        this.user = user;
        this.admin = admin;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void addAdminAndUserToDataBase(){
        roleService.save(new Role("ADMIN"));
        roleService.save(new Role("USER"));

        user.setName("Marc");
        user.setLastName("Hudson");
        user.setUsername("marc_hudson");
        user.setPassword("marc1987");
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.save(user);

        admin.setName("Nikita");
        admin.setLastName("Kotenkov");
        admin.setUsername("nikita_kotenkov");
        admin.setPassword("nikita2001");
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN"), roleService.getRoleByName("USER")));
        userService.save(admin);
    }
}