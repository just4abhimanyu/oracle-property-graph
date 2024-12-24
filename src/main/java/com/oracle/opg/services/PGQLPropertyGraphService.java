package com.oracle.opg.services;

import com.oracle.opg.repositories.PGQLGraphRepository;
import oracle.pgx.api.PgqlResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class PGQLPropertyGraphService{

    @Autowired
    private PGQLGraphRepository pgqlGraphRepository;

    public void createPGQLPropertyGraph(String pgViewName) throws SQLException {
        pgqlGraphRepository.createPGQLPropertyGraph(pgViewName);
    }
    public void findFriendOfFriends() throws SQLException {
        pgqlGraphRepository.findFriendOfFriends();
    }
    public Map<String,List<String>> findFriendsByName(String name){
        //List<Map<String, Object>> friends =
        Map<String,List<String>> friendList = new HashMap<>();
         friendList.put(name,parseFriendList(pgqlGraphRepository.findFriendsByName(name)));
    return friendList;
    }

    private List<String> parseFriendList(PgqlResultSet resultSet){
        // Process the result
        List<String> friends = new ArrayList<>();
        try {
            while (resultSet.next()) {
                friends.add(resultSet.getString(1));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
       return friends;
    }
}
