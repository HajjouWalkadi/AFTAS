package com.example.aftas.repository;

import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, RankingId> {
    Ranking findByMemberIdAndCompetitionId(Long memberId, Long competitionId);
}
