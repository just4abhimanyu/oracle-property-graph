package com.oracle.opg.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Map;
@Entity
public class Vertex {

    @Id
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
