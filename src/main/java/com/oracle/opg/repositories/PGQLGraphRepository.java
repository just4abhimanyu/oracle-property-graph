package com.oracle.opg.repositories;

import com.oracle.opg.models.Vertex;
import oracle.pgql.lang.PgqlException;
import oracle.pgx.api.Pgx;
import oracle.pgx.api.PgxGraph;
import oracle.pgx.api.PgxSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import oracle.pgx.api.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
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

        String sql = "CREATE PROPERTY GRAPH admin.student_network_pgql\n" +
                "  VERTEX TABLES (\n" +
                "    \"ADMIN\".\"PERSONS\"\n" +
                "      KEY ( \"PERSON_ID\" )\n" +
                "      PROPERTIES ( \"BIRTHDATE\", \"NAME\", \"PERSON_ID\" ),\n" +
                "    \"ADMIN\".\"UNIVERSITY\"\n" +
                "      KEY ( \"ID\" )\n" +
                "      PROPERTIES ( \"NAME\", \"ID\" )\n" +
                "  )\n" +
                "  EDGE TABLES (\n" +
                "    \"ADMIN\".\"FRIENDS\" KEY ( \"FRIENDSHIP_ID\" )\n" +
                "      SOURCE KEY ( \"PERSON_B\" ) REFERENCES \"PERSONS\"( \"PERSON_ID\" )\n" +
                "      DESTINATION KEY ( \"PERSON_A\" ) REFERENCES \"PERSONS\"( \"PERSON_ID\" )\n" +
                "      PROPERTIES ( \"FRIENDSHIP_ID\", \"MEETING_DATE\", \"PERSON_A\", \"PERSON_B\" ),\n" +
                "    \"ADMIN\".\"STUDENT_OF\" KEY ( \"S_ID\" )\n" +
                "      SOURCE KEY ( \"S_PERSON_ID\" ) REFERENCES \"PERSONS\"( \"PERSON_ID\" )\n" +
                "      DESTINATION KEY ( \"S_UNIV_ID\" ) REFERENCES \"UNIVERSITY\"( \"ID\" )\n" +
                "      PROPERTIES ( \"SUBJECT\", \"S_ID\", \"S_PERSON_ID\", \"S_UNIV_ID\" )\n" +
                "  )\n" +
                "  OPTIONS( TRUSTED MODE, DISALLOW MIXED PROPERTY TYPES )\n";
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

    public PgqlResultSet findFriendsByName(String name){
        String pgql = "SELECT friend.name \n" +
                "FROM MATCH All (p:Persons) -[:FRIENDS]-> {3}(friend:Persons) ON student_network_pgql\n" +
                "where p.name='"+name+"'";
        return executePGQLQuery(pgql);
    }
    public void findFriendOfFriends() throws SQLException {
        String pgql = "MATCH (p1:Persons)-[:FRIENDS*2..3]-(p3:Persons)\n" +
                "RETURN p1.name AS person1, p3.name AS person3, COUNT(*) AS connections";

        // Execute the PGQL query
        List<Map<String, Object>> results = jdbcTemplate.queryForList(pgql);

        // Process the results
        for (Map<String, Object> row : results) {
            System.out.println("Person 1: " + row.get("person1"));
            System.out.println("Person 3: " + row.get("person3"));
            System.out.println("Connections: " + row.get("connections"));
            System.out.println();
        }
    }
    private PgqlResultSet executePGQLQuery(String pgqlQuery){


        try (PgxSession session = Pgx.createSession("my-session")) {
            PgxGraph graph = session.getGraph("student_network_pgql");

            return graph.queryPgql(pgqlQuery);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
