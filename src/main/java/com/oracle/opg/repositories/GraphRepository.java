package com.oracle.opg.repositories;

import com.oracle.opg.dao.OraclePropertyGraphDao;
import com.oracle.opg.models.Bank;
import com.oracle.opg.models.Person;
import com.oracle.opg.models.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class GraphRepository {

    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public GraphRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String testConnection() {
        return jdbcTemplate.queryForObject("SELECT 'Hello, World!' FROM DUAL", String.class);
    }

    public List<Person> getAllPersons() {
        String sql = "SELECT PERSON_ID, NAME, BIRTHDATE FROM ADMIN.PERSONS";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Person person = new Person();
            person.setPersonId(rs.getString("PERSON_ID"));
            person.setName(rs.getString("NAME"));
            person.setBirthDate(rs.getString("BIRTHDATE"));
            return person;
        });
    }

//    public List<Map<String, Object>> fetchVertices(String graphName) {
//        String sql = "SELECT * FROM " + graphName + "_VERTICES WHERE property_name = ?";
//        return jdbcTemplate.queryForList(sql, "value");
//    }

    public List<Map<String, Object>> fetchEdges(String graphName) {
        String sql = "SELECT * FROM " + graphName + "_EDGES WHERE property_name = ?";
        return jdbcTemplate.queryForList(sql, "value");
    }

    public List<Vertex> getAllVertices(String graphName) {
        String sql = "SELECT * FROM " + graphName + " VERTICES";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Vertex vertex = new Vertex();
            vertex.setId(rs.getString("ID"));
            vertex.setLabel(rs.getString("LABEL"));

            // Extract all other properties into a Map
            Map<String, Object> properties = (Map<String, Object>) jdbcTemplate.getDataSource()
                    .getConnection()
                    .getMetaData()
                    .getColumns(null, null, graphName.toUpperCase() + "_VERTICES", null);
            vertex.setProperties(properties);

            return vertex;
        });
    }
}

