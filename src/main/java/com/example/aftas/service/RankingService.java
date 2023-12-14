package com.example.aftas.service;

import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;

public interface RankingService {

    Ranking getRankingById(RankingId id);
    Ranking getRankingByCompetitionIdAndMemberId(Long competitionId, Long memberId);
    Ranking addRanking(Ranking ranking);
    Ranking updateRanking(Ranking ranking, RankingId id);
    Ranking updateRankingScore(Ranking ranking, RankingId id);
    void deleteRanking(RankingId id);


}
