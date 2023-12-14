package com.example.aftas.repository;

import com.example.aftas.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
        Competition findByDate(LocalDate date);

        Optional<Competition> findByCode(String code);
}
