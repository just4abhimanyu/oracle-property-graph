package com.oracle.opg.repositories;

import com.oracle.opg.models.Vertex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PGQLGraphRepository  {



    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PGQLGraphRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createPGQLPropertyGraph(String pgViewName) throws SQLException {
        // Get vertex tables from the database
        List<String> vertexTables = getVertexTables();

        // Get edge tables from the database
        List<String> edgeTables = getEdgeTables();

        String sql = "CREATE PROPERTY GRAPH " + pgViewName + " " +
                "VERTEX TABLES (" +
                vertexTables.stream()
                        .map(table -> "\"ADMIN\".\"" + table + "\"")
                        .collect(Collectors.joining(", ")) +
                ") " +
                "EDGE TABLES (" +
                edgeTables.stream()
                        .map(table -> "\"ADMIN\".\"" + table + "\"")
                        .collect(Collectors.joining(", ")) +
                ") " +
                "OPTIONS( TRUSTED MODE, DISALLOW MIXED PROPERTY TYPES )";
        System.out.println("PGQL query -->\n"+sql);
        jdbcTemplate.execute(sql);
    }

    private List<String> getVertexTables() throws SQLException {
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT table_name FROM user_tables WHERE table_name in ('PERSONS','UNIVERSITY')");

        return results.stream()
                .map(result -> (String) result.get("table_name"))
                .collect(Collectors.toList());
    }

    private List<String> getEdgeTables() throws SQLException {
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT table_name FROM user_tables WHERE table_name in ('FRIENDS','STUDENT_OF')");

        return results.stream()
                .map(result -> (String) result.get("table_name"))
                .collect(Collectors.toList());
    }

//    @Query(value = "MATCH (v1)-[r:FRIEND*3]->(v2) RETURN v2", nativeQuery = true)
//    List<Vertex> findFriendOfFriendOfFriend(Long startId);



}
