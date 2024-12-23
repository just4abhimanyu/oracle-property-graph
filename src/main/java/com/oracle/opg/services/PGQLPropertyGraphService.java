package com.oracle.opg.services;

import com.oracle.opg.repositories.PGQLGraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

}
