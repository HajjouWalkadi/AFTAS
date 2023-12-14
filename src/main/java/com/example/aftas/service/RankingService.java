package com.example.aftas.service;

import com.example.aftas.domain.*;

import java.util.List;

public interface RankingService {

    Ranking getRankingById(RankingId id);
    Ranking getRankingByCompetitionIdAndMemberId(Long competitionId, Long memberId);
    //void updateRankingScoreAndRank(Member member, Competition competition, Fish fish);
    Ranking updateRanking(Ranking ranking, RankingId id);
   //Ranking updateRankingScore(Ranking ranking, RankingId id);
   List<Ranking> updateRankOfMemberInCompetition(String competitionCode);

    List<Ranking> findPodiumByCompetitionId(Long id);
    void deleteRanking(RankingId id);


}
