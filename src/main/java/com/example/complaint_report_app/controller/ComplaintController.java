package com.example.complaint_report_app.controller;

import com.example.complaint_report_app.dao.ComplaintDao;
import com.example.complaint_report_app.dao.UserDao;
import com.example.complaint_report_app.model.Complaints;
import com.example.complaint_report_app.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ComplaintController {

    @Autowired
    private UserDao udao;

    @Autowired
    private ComplaintDao dao;

    @CrossOrigin(value = "*")
    @PostMapping(path ="/register", consumes = "application/json", produces = "application/son")
    public String userRegister(@RequestBody Users u)
    {
        System.out.println(u.getName());
        udao.save(u);
        return "added";
    }

    @CrossOrigin(value = "*")
    @PostMapping(path ="/userlogin", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> userLogin(@RequestBody Users u)
    {
        System.out.println(u.getEmail());
        System.out.println(u.getPassword());
        HashMap<String,String> map = new HashMap<>();
        List<Users> result= udao.userAuth(u.getEmail(),u.getPassword());
        if(result.size()==0)
        {
            map.put("failed","invalid credential");
        }
        else {
            int id = result.get(0).getId();
            map.put("status","success");
            map.put("id",String.valueOf(id));
        }

        return map;
    }

    @CrossOrigin(value = "*")
    @PostMapping(path ="/userDetails", consumes = "application/json", produces = "application/json")
    public List<Users> userDetails(@RequestBody Users u)
    {
        return udao.userDetails(u.getId());
    }

    @CrossOrigin(value = "*")
    @PostMapping(path ="/addcomplaint", consumes = "application/json", produces = "application/json")
    public HashMap<String,String> addComplaint(@RequestBody Complaints c)
    {
        HashMap<String,String> map =new HashMap<>();
        System.out.println(c.getComplaint());
        dao.save(c);
        map.put("status","success");
        return map;
    }

    @CrossOrigin(value = "*")
    @GetMapping(path ="/viewcomplaint")
    public List<Map<String,String> >viewComplaint()
    {

        return (List<Map<String,String>>)  dao.viewComplaints();
    }


}
