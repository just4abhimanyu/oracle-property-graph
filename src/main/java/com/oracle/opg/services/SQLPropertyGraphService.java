package com.oracle.opg.services;

import com.oracle.opg.dao.OraclePropertyGraphDao;
import com.oracle.opg.models.*;
import com.oracle.opg.repositories.SQLGraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public class SQLPropertyGraphService {


    private final SQLGraphRepository SQLGraphRepository;

    private final OraclePropertyGraphDao propertyGraphDao;

    @Autowired
    public SQLPropertyGraphService(OraclePropertyGraphDao propertyGraphDao, SQLGraphRepository SQLGraphRepository) {
        this.propertyGraphDao = propertyGraphDao;
        this.SQLGraphRepository = SQLGraphRepository;
    }
    public String checkAutonomousDBConnection() {
        return SQLGraphRepository.testConnection();
    }

    public List<Person> fetchAllPersons() {
        return  SQLGraphRepository.getAllPersons();
    }
    public List<University> fetchAllUniversities() {
        return SQLGraphRepository.getAllUniversities();
    }
    public List<Friends> fetchAllFriendships() {
        return SQLGraphRepository.getAllFriendships();
    }
    public List<StudentOf> fetchAllStudentRelationships(){
        return SQLGraphRepository.getAllStudentRelationships();
    }
    public void createSQLPropertyGraph() throws SQLException {
        SQLGraphRepository.createSQLPropertyGraph();
    }
}
