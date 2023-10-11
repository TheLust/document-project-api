package com.iongroup.documentprojectapi;

import com.iongroup.documentprojectapi.entity.Role;
import com.iongroup.documentprojectapi.entity.User;
import com.iongroup.documentprojectapi.service.RoleService;
import com.iongroup.documentprojectapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@Component
@RequiredArgsConstructor
class DocumentProjectApiApplicationTests {

	private final UserService userService;
	private final RoleService roleService;

	@Test
	void contextLoads() {
		User user = new User();
		user.setPassword("admin");
		user.setEmail("admin@admin.admin");
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.findByName("Amministratore").get());
		user.setRoles(roles);
		user.setIsEnabled(true);
		user.setName("Admin");
		user.setSurname("Admin");
		user.setPatronymic("Admin");
		user.setUsername("admin2");

		userService.save(user);
	}

}
