package com.example.aftas.service;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CompetitionService {

    Competition getCompetitionById(Long code);
    Competition findByCode(String code);
    Competition addCompetition(Competition competition);
    List<Competition> getAllCompetitions();
    List<Competition> getAllCompetitionsPaginated(Pageable pageable);
    Competition updateCompetition(Competition competition, Long id);
    void deleteCompetition(Long id);
    //Ranking rankingCompetitionResult(Ranking ranking, RankingId id);
    Ranking registerMemberCompetition(Ranking ranking);

}
