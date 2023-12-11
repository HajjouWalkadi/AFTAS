package com.example.aftas.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Range;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Code cannot be empty")
    @Size(min = 6, max = 20, message = "Code must be between 6 and 20 characters")
    private String code;

    @NotNull(message = "date cannot be empty")
    //@PastOrPresent(message = "Date must be in the past or present")
    private String date;

    @NotNull(message = "Start time cannot be empty")
    private String startTime;

    @NotNull(message = "End time cannot be empty")
    private String endTime;

    @NotNull(message = "Number of participants cannot be empty")
    //@Range(min = 10, max = 100, message = "Number of participants must be between 10 and 100 characters")
    private int numberOfParticipants;

    @NotNull(message = "Location cannot be empty")
    //@Pattern(regexp = "^[a-zA-Z0-9\\s,.-]+$", message = "Invalid location format")
    private String location;

    @NotNull(message = "Amount cannot be empty")
    @Positive(message = "Amount must be positive")
    private Double amount;


    @OneToMany(mappedBy = "competition")
    @Cascade(CascadeType.ALL)
    private List<Ranking> rankings;
}
