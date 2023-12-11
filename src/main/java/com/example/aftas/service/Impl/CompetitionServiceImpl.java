package com.example.aftas.service.Impl;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.service.CompetitionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    @Override
    public Competition addCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public List<Competition> searchCompetition(String code) {
        return competitionRepository.findByCode(code);
    }

    @Override
    public Competition updateCompetition(Competition competition, Long id) {
        Competition competition1 = getCompetitionById(id);
        competition1.setCode(competition.getCode());
        competition1.setDate(competition.getDate());
        competition1.setStartTime(competition.getStartTime());
        competition1.setEndTime(competition.getEndTime());
        competition1.setNumberOfParticipants(competition.getNumberOfParticipants());
        competition1.setLocation(competition.getLocation());
        competition1.setAmount(competition.getAmount());

        return competitionRepository.save(competition1);
    }

    @Override
    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);

    }
}
