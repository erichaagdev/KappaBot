package com.gorlah.kappabot.jpa.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meme {
    
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Integer id;
    
    private String memeName;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String parameters;
    
    @Column(nullable = false)
    private String url;
    
    @Column(nullable = false)
    private String user;
    
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp added;
    
    private int useCount;
    
    public void incrementUseCount() {
        useCount++;
    }
}
