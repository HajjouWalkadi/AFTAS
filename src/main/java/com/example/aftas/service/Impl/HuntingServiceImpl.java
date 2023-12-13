package com.example.aftas.service.Impl;

import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Ranking;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.HuntingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuntingServiceImpl implements HuntingService {

    private HuntingRepository huntingRepository;
    private MemberServiceImpl memberService;
    private CompetitionServiceImpl competitionService;
    private FishServiceImpl fishService;
    private RankingServiceImpl rankingService;

    public HuntingServiceImpl(HuntingRepository huntingRepository, MemberServiceImpl memberService, CompetitionServiceImpl competitionService, FishServiceImpl fishService, RankingServiceImpl rankingService) {
        this.huntingRepository = huntingRepository;
        this.memberService = memberService;
        this.competitionService = competitionService;
        this.fishService = fishService;
        this.rankingService = rankingService;
    }


    @Override
    public Hunting getHuntingById(Long id) {
        return huntingRepository.findById(id).orElseThrow(() -> new RuntimeException("Hunting id " + id + "not found"));
    }

    @Override
    public Hunting addHunting(Hunting hunting) {
        Long memberId = hunting.getMember().getId();
        Long competitionId = hunting.getCompetition().getId();
        Long fishId = hunting.getFish().getId();

        memberService.getMemberById(memberId);
        competitionService.getCompetitionById(competitionId);
        fishService.getFishById(fishId);
        rankingService.getRankingByCompetitionIdAndMemberId(competitionId, memberId);
        Hunting hunting1 = huntingRepository.findByCompetitionIdAndMemberIdAndFishId(competitionId, memberId, fishId);

        Ranking ranking = rankingService.getRankingByCompetitionIdAndMemberId(competitionId, memberId);
        ranking.setScore(ranking.getScore() + hunting.getFish().getLevel().getPoints());
        rankingService.updateRanking(ranking, ranking.getId());

        if (hunting1 != null) {
            hunting1.setNumberOfFish(hunting1.getNumberOfFish() + 1);
            return huntingRepository.save(hunting1);
        }else {
            return huntingRepository.save(hunting);
        }
    }

    @Override
    public List<Hunting> getHuntingsByCompetition(Long competitionId) {
        return huntingRepository.findByCompetitionId(competitionId);
    }

    @Override
    public List<Hunting> getHuntingsByCompetitionAndMember(Long competitionId, Long memberId) {
        return huntingRepository.findByCompetitionIdAndMemberId(competitionId, memberId);
    }


    @Override
    public void deleteHunting(Long id) {
        getHuntingById(id);
        huntingRepository.deleteById(id);
    }
}
