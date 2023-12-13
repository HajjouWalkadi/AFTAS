package com.example.aftas.service.Impl;

import com.example.aftas.domain.Ranking;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.RankingService;
import org.springframework.stereotype.Service;

@Service
public class RankingServiceImpl implements RankingService {
    private RankingRepository rankingRepository;
    private CompetitionServiceImpl competitionService;
    private MemberServiceImpl memberService;

    public RankingServiceImpl(RankingRepository rankingRepository, CompetitionServiceImpl competitionService, MemberServiceImpl memberService) {
        this.rankingRepository = rankingRepository;
        this.competitionService = competitionService;
        this.memberService = memberService;
    }


    @Override
    public Ranking getRankingById(Long id) {
        return rankingRepository.findById(id).orElseThrow(() -> new RuntimeException("Ranking id " + id + "not found"));
    }

    @Override
    public Ranking getRankingByCompetitionIdAndMemberId(Long competitionId, Long memberId) {
        
        return null;
    }

    @Override
    public Ranking addRanking(Ranking ranking) {
        return null;
    }

    @Override
    public Ranking updateRanking(Ranking ranking, Long id) {
        return null;
    }

    @Override
    public Ranking updateRankingScore(Ranking ranking, Long id) {
        return null;
    }

    @Override
    public void deleteRanking(Long id) {

    }
}
