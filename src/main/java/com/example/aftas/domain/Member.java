package com.example.aftas.domain;

import com.example.aftas.enums.IdentityDocumentType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private Integer referenceNumber;

    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 40, message = "Name must be between 3 and 40 characters")
    private String name;

    @NotNull(message = "Family name cannot be null")
    @Size(min = 3, max = 40, message = "Family name must be between 3 and 40 characters")
    private String familyName;

    @NotNull(message = "Access date cannot be null")
    @PastOrPresent(message = "Access date must be in the past or present")
    @Temporal(TemporalType.DATE)
    private LocalDate accessionDate;

    @NotNull(message = "Nationality cannot be empty")
    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;

    @NotNull(message = "Identity cannot be null")
    //@Size(min = 6, max = 14, message = "Identity must be between 6 and 14 characters")
    @Column(unique = true)
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
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
