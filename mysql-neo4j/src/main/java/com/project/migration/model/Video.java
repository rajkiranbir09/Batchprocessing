package com.project.migration.model;


import lombok.Data;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@Data
@NodeEntity
public class Video {

    @Id
    @GeneratedValue
    private String uuid;

    private String fileName;

    private String bitrateKbps;

    private String remotePath;

    private String title;



    // Neo4J : id, name, bitrateKbps, remotePath
    // remote : title



}
