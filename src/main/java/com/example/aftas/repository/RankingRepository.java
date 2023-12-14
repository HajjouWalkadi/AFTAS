package com.example.aftas.repository;

import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
    Ranking findByMemberIdAndCompetitionId(Long memberId, Long competitionId);
    //Optional<Ranking> findByCompetitionCodeAndMemberNum(String code, Integer num);
    //List<Ranking> findByCompetitionCodeOrderByScoreDesc(String code);
    List<Ranking> findAllByCompetitionCode(String competitionCode);

    List<Ranking> findAllByCompetitionId(Long Id);
    List<Ranking> findTop3ByCompetitionIdOrderByScoreDesc(Long id);
    List<Ranking> findTop3ByCompetitionIdOrderByRankAsc(Long id);
}
