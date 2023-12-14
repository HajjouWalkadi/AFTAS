package com.example.aftas.service.Impl;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.RankingService;
import org.springframework.stereotype.Service;

@Service
public class RankingServiceImpl implements RankingService {
    private RankingRepository rankingRepository;
    private CompetitionRepository competitionRepository;
    private MemberServiceImpl memberService;

    public RankingServiceImpl(RankingRepository rankingRepository, CompetitionRepository competitionRepository, MemberServiceImpl memberService) {
        this.rankingRepository = rankingRepository;
        this.competitionRepository = competitionRepository;
        this.memberService = memberService;
    }


    @Override
    public Ranking getRankingById(RankingId id) {
        return rankingRepository.findById(id).orElseThrow(() -> new RuntimeException("Ranking id " + id + "not found"));
    }

    @Override
    public Ranking getRankingByCompetitionIdAndMemberId(Long competitionId, Long memberId) {
        //check if competition exists
        competitionRepository.findById(competitionId).orElseThrow(()-> new RuntimeException("Competition with id :" + competitionId + " not found"));
        Ranking ranking = rankingRepository.findByMemberIdAndCompetitionId(memberId,competitionId);
        if (ranking == null) {
            throw new RuntimeException("Member id " + memberId + " not found");
        }
        //check if member exists
        memberService.getMemberById(memberId);
        return ranking;

    }

    @Override
    public Ranking addRanking(Ranking ranking) {
        Member member = memberService.getMemberById(ranking.getId().getMemberId());
        Competition competition = competitionRepository.findById(ranking.getId().getCompetitionId()).orElse(null);
        if(member == null || competition == null) {
            throw new RuntimeException("Competition or Member not found with id given");
        }
        ranking.setMember(member);
        ranking.setCompetition(competition);
        return rankingRepository.save(ranking);
    }

    @Override
    public Ranking updateRanking(Ranking ranking, RankingId id) {
        Ranking ranking1 = rankingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ranking not found"));
        ranking1.setRank(ranking.getRank());
        ranking1.setScore(ranking.getScore());
        return rankingRepository.save(ranking1);
    }

    @Override
    public Ranking updateRankingScore(Ranking ranking, RankingId id) {
        Ranking ranking1 = getRankingById(id);
        ranking1.setScore(ranking.getScore()+ranking1.getScore());
        return rankingRepository.save(ranking1);
    }

    @Override
    public void deleteRanking(RankingId id) {
        getRankingById(id);
        rankingRepository.deleteById(id);

    }
}
