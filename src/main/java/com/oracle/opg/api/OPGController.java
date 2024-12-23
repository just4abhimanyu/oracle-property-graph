package com.oracle.opg.api;

import com.oracle.opg.models.*;
import com.oracle.opg.services.PropertyGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OPGController {

    @Autowired
    private PropertyGraphService graphService;

    @GetMapping("/checkDBConnection")
    public String testDBConnection(){
        return graphService.checkAutonomousDBConnection();
    }
    @GetMapping("/persons")
    public List<Person> fetchAllPersons() {
        return  graphService.fetchAllPersons();
    }
    @GetMapping("/universities")
    public List<University> fetchAllUniversities(){
        return graphService.fetchAllUniversities();
    }
    @GetMapping("/friendships")
    public List<Friends> getFriendships() {
        return graphService.fetchAllFriendships();
    }
    @GetMapping("/studentRelationships")
    public List<StudentOf> getStudentRelationships(){
        return graphService.fetchAllStudentRelationships();
    }
}
