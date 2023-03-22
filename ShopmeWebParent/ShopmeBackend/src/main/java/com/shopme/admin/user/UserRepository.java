package com.shopme.admin.user;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shopme.common.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("select * from User u where u.email= :email")
    public User getUserByEmail(@Param("email") String email);

    public Long countById(Integer id);

}
