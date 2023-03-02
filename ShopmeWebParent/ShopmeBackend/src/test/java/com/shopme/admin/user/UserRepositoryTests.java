package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUserWithOneRole() {
	User userYash = new User("yash@gmail.com", "yash{}12!", "Yash", "Sharma");
	Role role = entityManager.find(Role.class, 1);
	userYash.addRole(role);
	User savedYash = repo.save(userYash);
	assertThat(savedYash.getId()).isGreaterThan(0);
    }

    @Test
    public void testCreateUserWithMultipleRole() {
	User userSunil = new User("sunil@gmail.com", "sunil{}12!", "Sunil", "Singh");
	Role role1 = entityManager.find(Role.class, 1);
	Role role2 = entityManager.find(Role.class, 2);
	Role role3 = entityManager.find(Role.class, 3);
	userSunil.addRole(role1);
	userSunil.addRole(role2);
	userSunil.addRole(role3);
	User savedSunil = repo.save(userSunil);
	assertThat(savedSunil.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAllUsers() {
	Iterable<User> listUsers = repo.findAll();
	listUsers.forEach(ob -> System.out.println(ob));
    }

    @Test
    public void testGetUserById() {
	User usr = repo.findById(1).get();
	System.out.println(usr);
	assertThat(usr.getId()).isNotNull();
    }

}
