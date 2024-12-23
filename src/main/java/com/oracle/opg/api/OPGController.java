package com.oracle.opg.api;

import com.oracle.opg.models.Bank;
import com.oracle.opg.models.Person;
import com.oracle.opg.models.Vertex;
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
    @GetMapping("/vertices")
    public List<Vertex> getAllVertices(@RequestParam String graphName) {
        System.out.println("------Graph data fetched successfully-----------"+graphName);
        return graphService.fetchAllVertices(graphName);
    }
    @GetMapping("/persons")
    public List<Person> fetchAllPersons() {
        return  graphService.fetchAllPersons();
    }
}
