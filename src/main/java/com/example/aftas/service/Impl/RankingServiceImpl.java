package com.example.aftas.service.Impl;

import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.RankingService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

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
        return rankingRepository.findById(id).orElseThrow(() -> new RuntimeException("Ranking with id " + id + " not found"));
    }

    @Override
    public Ranking getRankingByCompetitionIdAndMemberId(Long competitionId, Long memberId) {
        //check if member exists
        memberService.getMemberById(memberId);
        //check if competition exists
        competitionRepository.findById(competitionId).orElseThrow(() -> new RuntimeException("Competition with id :" + competitionId + " not found"));

        Ranking ranking = rankingRepository.findByMemberIdAndCompetitionId(memberId, competitionId);
        if (ranking == null) {
            throw new RuntimeException("Member with id " + memberId + " not registred in this competition");
        }

        return ranking;

    }



    @Override
    public List<Ranking> findPodiumByCompetitionId(Long id) {
        List<Ranking> allRankings = rankingRepository.findAllByCompetitionId(id).stream().sorted(Comparator.comparingInt(Ranking::getScore).reversed()).toList();
        allRankings.forEach(r -> r.setRank(allRankings.indexOf(r) + 1));
        rankingRepository.saveAll(allRankings);
        List<Ranking> rankings = rankingRepository.findTop3ByCompetitionIdOrderByRankAsc(id);
        if (rankings.isEmpty()) {
            throw new RuntimeException("No podium found");
        } else {
            return rankings;
        }
    }

    @Override
    public Ranking updateRanking(Ranking ranking, RankingId id) {
        Ranking ranking1 = rankingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Ranking not found"));
        ranking1.setRank(ranking.getRank());
        ranking1.setScore(ranking.getScore());
        return rankingRepository.save(ranking1);
    }

    @Override
    public List<Ranking> updateRankOfMemberInCompetition(String competitionCode) {
        List<Ranking> rankings = rankingRepository.findAllByCompetitionCode(competitionCode);

        // check if there is any ranking for the competition
        if(rankings == null){
            throw new ResourceNotFoundException("Rankings for competition code " + competitionCode + " not found");
        }
        // sort the rankings by score and update the rank
        // rankings.sort((r1, r2) -> r2.getScore().compareTo(r1.getScore()));

        // update the rank
        for(int i = 0; i < rankings.size(); i++){
            rankings.get(i).setRank(i + 1);
        }

        // save the rankings
        return rankingRepository.saveAll(rankings);
    }

  /*  @Override
    public Ranking updateRankingScore(Ranking ranking, RankingId id) {
        Ranking ranking1 = getRankingById(id);
        ranking1.setScore(ranking.getScore()+ranking1.getScore());
        return rankingRepository.save(ranking1);
    }*/

    @Override
    public void deleteRanking(RankingId id) {
        getRankingById(id);
        rankingRepository.deleteById(id);

    }
}
