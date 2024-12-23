package com.oracle.opg.services;

import com.oracle.opg.dao.OraclePropertyGraphDao;
import com.oracle.opg.models.Bank;
import com.oracle.opg.models.Person;
import com.oracle.opg.models.Vertex;
import com.oracle.opg.repositories.GraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class PropertyGraphService {


    private final GraphRepository graphRepository;

    private final OraclePropertyGraphDao propertyGraphDao;

    @Autowired
    public PropertyGraphService(OraclePropertyGraphDao propertyGraphDao,GraphRepository graphRepository) {
        this.propertyGraphDao = propertyGraphDao;
        this.graphRepository = graphRepository;
    }
    public String checkAutonomousDBConnection() {
        return graphRepository.testConnection();
    }

    public List<Vertex> fetchAllVertices(String graphName){
        return  graphRepository.getAllVertices(graphName);
    }
    public List<Person> fetchAllPersons() {
        return  graphRepository.getAllPersons();
    }
}
