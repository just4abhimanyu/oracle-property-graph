package com.oracle.opg.api;

import com.oracle.opg.services.PGQLPropertyGraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

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
}
