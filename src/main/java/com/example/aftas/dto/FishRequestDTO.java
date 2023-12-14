package com.example.aftas.dto;

import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Level;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public record FishRequestDTO (
        @NotNull(message = "Name is required")
        @Size(min = 3, max = 60, message = "Name must be between 3 and 60 characters")
        @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Name must be alphanumeric")
        @Column(unique = true)
        String name,

        @NotNull(message = "Average weight is required")
        @Range(min = 0, max = 1000, message = "Weight must be between 0 and 1000")
        @Positive(message = "Weight must be greater than 0")
        double averageWeight,

        Long levelId
){
    public Fish toFish() {
        Fish.FishBuilder fishBuilder = new Fish().builder()
                .name(name)
                .averageWeight(averageWeight);
        if(levelId != null) {
            fishBuilder.level(Level.builder().id(levelId).build());
        }
        return fishBuilder.build();
    }
}
