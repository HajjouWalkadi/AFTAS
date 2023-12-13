package com.example.aftas.service;

import com.example.aftas.domain.Ranking;

public interface RankingService {

    Ranking getRankingById(Long id);
    Ranking getRankingByCompetitionIdAndMemberId(Long competitionId, Long memberId);
    Ranking addRanking(Ranking ranking);
    Ranking updateRanking(Ranking ranking, Long id);
    Ranking updateRankingScore(Ranking ranking, Long id);
    void deleteRanking(Long id);


}
