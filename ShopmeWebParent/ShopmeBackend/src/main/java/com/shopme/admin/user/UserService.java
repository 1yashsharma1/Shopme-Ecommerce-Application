package com.shopme.admin.user;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder encoder;

    public List<User> listAll() {
	return (List<User>) userRepo.findAll();
    }

    public List<Role> listRoles() {
	return (List<Role>) roleRepo.findAll();
    }

    public void save(User user) {

	boolean isUpdatingUser = (user.getId() != null);

	if (isUpdatingUser) {
	    User existingUser = userRepo.findById(user.getId()).get();

	    if (user.getPassword().isEmpty()) {
		user.setPassword(existingUser.getPassword());
	    } else {
		encodePassword(existingUser);
	    }
	} else {
	    encodePassword(user);
	}
	userRepo.save(user);
    }

    public void encodePassword(User user) {
	String encodedPass = encoder.encode(user.getPassword());
	user.setPassword(encodedPass);
    }

    public boolean isEmailUnique(Integer id, String email) {
	User userByEmail = userRepo.getUserByEmail(email);
	if (userByEmail == null)
	    return true;
	// creating new user
	boolean isCreatingNew = (id == null);
	if (isCreatingNew) {
	    if (userByEmail != null) {
		return false;
	    }
	}
	// if editing a existing user
	else {
	    if (userByEmail.getId() != id) {
		return false;
	    }
	}

	return true;
    }

    public User get(Integer id) throws UserNotFoundException {
	try {
	    return userRepo.findById(id).get();
	} catch (NoSuchElementException e) {
	    throw new UserNotFoundException("User with id : " + id + " is not present");
	}
    }

    public void delete(Integer id) throws UserNotFoundException {
	Long countById = userRepo.countById(id);
	if (countById == null || countById == 0) {
	    throw new UserNotFoundException("Could not find any user with ID:" + id);
	}
	userRepo.deleteById(id);
    }

}
