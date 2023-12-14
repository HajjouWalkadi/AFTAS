package com.example.aftas.service.Impl;

import com.example.aftas.domain.Competition;
import com.example.aftas.handler.exception.OperationException;
import com.example.aftas.repository.CompetitionRepository; // Assuming this repository exists
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompetitionServiceImplTest {

    @Mock
    private CompetitionRepository competitionRepository; // Mock the repository

    @InjectMocks
    private CompetitionServiceImpl competitionService; // Inject the service

    @Test
    public void test_addCompetition_sameDateException() {
        // Arrange
        Competition existingCompetition = new Competition();
        existingCompetition.setDate(LocalDate.now());
        existingCompetition.setStartTime(LocalTime.of(9, 0));
        existingCompetition.setEndTime(LocalTime.of(10, 0));
        existingCompetition.setLocation("Location");
        existingCompetition.setAmount(100);

        // Mocking repository behavior
        when(competitionRepository.save(existingCompetition)).thenReturn(existingCompetition);

        Competition competition = new Competition();
        competition.setDate(LocalDate.now());
        competition.setStartTime(LocalTime.of(11, 0));
        competition.setEndTime(LocalTime.of(12, 0));
        competition.setLocation("Location");
        competition.setAmount(200);

        // Mocking service behavior
        when(competitionService.addCompetition(competition)).thenThrow(OperationException.class);

        // Act & Assert
        assertThrows(OperationException.class, () -> competitionService.addCompetition(competition));
    }
}
