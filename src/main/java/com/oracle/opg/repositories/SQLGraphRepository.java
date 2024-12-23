package com.oracle.opg.repositories;

import com.oracle.opg.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SQLGraphRepository {


    @Autowired
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public SQLGraphRepository(JdbcTemplate jdbcTemplate) {
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
    public List<University> getAllUniversities() {
        String sql = "SELECT ID, NAME FROM ADMIN.UNIVERSITY";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            University university = new University();
            university.setId(rs.getString("ID"));
            university.setName(rs.getString("NAME"));
            return university;
        });
    }
    public List<Friends> getAllFriendships() {
        String sql = "SELECT FRIENDSHIP_ID, PERSON_A, PERSON_B, MEETING_DATE FROM ADMIN.FRIENDS";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Friends friends = new Friends();
            friends.setFriendshipId(rs.getString("FRIENDSHIP_ID"));
            friends.setPersonA(rs.getString("PERSON_A"));
            friends.setPersonB(rs.getString("PERSON_B"));
            friends.setMeetingDate(rs.getString("MEETING_DATE"));
            return friends;
        });
    }
    public List<StudentOf> getAllStudentRelationships() {
        String sql = "SELECT S_ID, S_PERSON_ID, S_UNIV_ID, SUBJECT FROM ADMIN.STUDENT_OF";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            StudentOf studentOf = new StudentOf();
            studentOf.setsId(rs.getString("S_ID"));
            studentOf.setsPersonId(rs.getString("S_PERSON_ID"));
            studentOf.setsUnivId(rs.getString("S_UNIV_ID"));
            studentOf.setSubject(rs.getString("SUBJECT"));
            return studentOf;
        });
    }


    public List<Map<String, Object>> fetchEdges(String graphName) {
        String sql = "SELECT * FROM " + graphName + "_EDGES WHERE property_name = ?";
        return jdbcTemplate.queryForList(sql, "value");
    }



}

