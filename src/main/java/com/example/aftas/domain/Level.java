package com.example.aftas.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private int code;
    private String description;
    private int points;

    @OneToMany(mappedBy = "level",cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Fish> fishes;
}

