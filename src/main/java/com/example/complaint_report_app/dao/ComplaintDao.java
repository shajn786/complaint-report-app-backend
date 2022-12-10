package com.example.complaint_report_app.dao;

import com.example.complaint_report_app.model.Complaints;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ComplaintDao extends CrudRepository<Complaints, Integer> {

    @Query(value = "SELECT  u.`address`,u. `confirmpassword`, u.`email`, u.`name`, u. `phno`, c.`complaint` FROM `users` u JOIN complaints c ON c.userid= u.id",nativeQuery = true)
    List<Map<String,String>> viewComplaints();
}

