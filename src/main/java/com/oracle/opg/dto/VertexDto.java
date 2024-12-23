package com.oracle.opg.dto;

import java.util.Map;

public class VertexDto {
    private String id; // Vertex ID
    private String label; // Vertex label
    private Map<String, Object> properties; // Additional properties

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public Map<String, Object> getProperties() { return properties; }
    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
