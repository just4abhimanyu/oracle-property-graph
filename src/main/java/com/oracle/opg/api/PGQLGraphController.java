package com.oracle.opg.api;

import com.oracle.opg.services.PGQLPropertyGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class PGQLGraphController {

    @Autowired
    private PGQLPropertyGraphService pgqlPropertyGraphService;

    @PostMapping("/create-pgql-graph")
    public String createPGQLPropertyGraph(@RequestParam String pgViewName) {
        try {
            pgqlPropertyGraphService.createPGQLPropertyGraph(pgViewName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "PGQL Graph created successfully....";
    }
    @GetMapping("/friend-of-friend")
    public String findFriendOfFriends() throws SQLException {
        pgqlPropertyGraphService.findFriendOfFriends();
        return "Fetched successfully ....";
    }
    @GetMapping("/findFriendByName")
    public Map<String,List<String>> findFriendsByName(@RequestParam String name){
        return pgqlPropertyGraphService.findFriendsByName(name);
    }
}
