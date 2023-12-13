package com.example.aftas.service;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompetitionService {

    Competition getCompetitionById(Long id);
    Competition addCompetition(Competition competition);
    List<Competition> getAllCompetitions();
    List<Competition> searchCompetition(String code);
    Competition updateCompetition(Competition competition, Long id);
    void deleteCompetition(Long id);
    Ranking rankingCompetitionResult(Ranking ranking, Long id);
    Ranking registerMemberCompetition(Ranking ranking);

}
