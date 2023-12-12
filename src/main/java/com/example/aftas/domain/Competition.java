package com.example.aftas.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Code cannot be empty")
    @Column(unique = true)
    private String code;

    @NotNull(message = "date cannot be empty")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "Start time cannot be empty")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @NotNull(message = "End time cannot be empty")
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;

    @NotNull(message = "Number of participants cannot be empty")
    @Min(value = 0, message = "Amount must be greater than 0")
    //@Range(min = 2, max = 100, message = "Number of participants must be between 10 and 100 characters")
    private int numberOfParticipants;

    @NotNull(message = "Location cannot be empty")
    @Size(min = 2, max = 50, message = "Location must be between 2 and 50 characters")
    //@Pattern(regexp = "^[a-zA-Z0-9\\s,.-]+$", message = "Invalid location format")
    private String location;

    @NotNull(message = "Amount cannot be empty")
    @Positive(message = "Amount must be positive")
    @Min(value = 0, message = "Amount must be greater than 0")
    private int amount;


    @OneToMany(mappedBy = "competition")
    //@Cascade(CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Ranking> ranking;

    @OneToMany(mappedBy = "competition")
    private List<Hunting> hunting;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;
}
