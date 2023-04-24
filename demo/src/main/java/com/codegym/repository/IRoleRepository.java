package com.codegym.repository;

import com.codegym.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

    @Query(value = "select * from role", nativeQuery = true)
    List<Role> finAllRole();


    @Modifying
    @Transactional
    @Query(value = "insert into user_role (username, role_id) values (:email, 3)", nativeQuery = true)
    void insertRoleGmail(String email);

    @Query(value = "select role.* from role " +
            "join user_role on role.id = user_role.role_id where user_role.username =:username", nativeQuery = true)
    List<Role> findRoleByUsername(@Param("username") String username);
}
