package com.example.aftas.repository;

import com.example.aftas.domain.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HuntingRepository extends JpaRepository<Hunting, Long> {
    List<Hunting> findByCompetitionId(Long CompetitionId);
    List<Hunting> findByCompetitionIdAndMemberId(Long competitionId, Long memberId);
    Hunting findByCompetitionIdAndMemberIdAndFishId(Long competitionId, Long memberId, Long fishId);

}
