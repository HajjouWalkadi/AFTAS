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
        //check if competition exists
        competitionService.getCompetitionById(competitionId);
        Ranking ranking = rankingRepository.findByMemberIdAndCompetitionId(memberId,competitionId);
        if (ranking == null) {
            throw new RuntimeException("Member id " + memberId + "not found");
        }
        //check if member exists
        memberService.getMemberById(memberId);
        return ranking;

    }

    @Override
    public Ranking addRanking(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    @Override
    public Ranking updateRanking(Ranking ranking, Long id) {
        Ranking ranking1 = getRankingById(id);
        ranking1.setRank(ranking.getRank());
        ranking1.setScore(ranking.getScore());
        return rankingRepository.save(ranking1);
    }

    @Override
    public Ranking updateRankingScore(Ranking ranking, Long id) {
        Ranking ranking1 = getRankingById(id);
        ranking1.setScore(ranking.getScore()+ranking1.getScore());
        return rankingRepository.save(ranking1);
    }

    @Override
    public void deleteRanking(Long id) {
        getRankingById(id);
        rankingRepository.deleteById(id);

    }
}
