package com.axyya.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.axyya.model.Role;
import com.axyya.model.User;

import java.util.Optional;

/**
 * @author sa
 * @date 10.10.2021
 * @time 11:27
 */
public interface UserRepository extends JpaRepository<User, Long>
{
    //findBy+fieldName

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User set role = :role where username = :username")
    void updateUserRole(@Param("username") String username, @Param("role") Role role);
}
