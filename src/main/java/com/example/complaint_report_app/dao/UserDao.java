package com.example.complaint_report_app.dao;

import com.example.complaint_report_app.model.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

public interface UserDao extends CrudRepository<Users, Integer> {

    @Query(value = "SELECT `id`, `address`, `confirmpassword`, `email`, `name`, `password`, `phno` FROM `users` WHERE `email` =:email AND `password`= :password", nativeQuery = true)
    List<Users> userAuth(@Param("email") String email, @Param("password") String password);


    @Query(value = "SELECT `id`, `address`, `confirmpassword`, `email`, `name`, `password`, `phno` FROM `users` WHERE `id`= :id", nativeQuery = true)
    List<Users> userDetails(@Param("id") Integer id);


}