package com.example.aftas.service.Impl;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;
import com.example.aftas.handler.exception.OperationException;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.service.CompetitionService;
import com.example.aftas.service.MemberService;
import com.example.aftas.service.RankingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final RankingService rankingService;
    private final MemberService memberService;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, RankingService rankingService, MemberService memberService) {
        this.competitionRepository = competitionRepository;
        this.rankingService = rankingService;
        this.memberService = memberService;
    }

    @Override
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    @Override
    public Competition addCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public List<Competition> searchCompetition(String code) {
        return competitionRepository.findByCode(code);
    }

    @Override
    public Competition updateCompetition(Competition competition, Long id) {
        Competition competition1 = getCompetitionById(id);
        competition1.setCode(competition.getCode());
        competition1.setDate(competition.getDate());
        competition1.setStartTime(competition.getStartTime());
        competition1.setEndTime(competition.getEndTime());
        competition1.setNumberOfParticipants(competition.getNumberOfParticipants());
        competition1.setLocation(competition.getLocation());
        competition1.setAmount(competition.getAmount());

        return competitionRepository.save(competition1);
    }

    @Override
    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);

    }

    @Override
    public Ranking rankingCompetitionResult(Ranking ranking, RankingId id) {
        return rankingService.updateRankingScore(ranking, id);
    }

    @Override
    public Ranking registerMemberCompetition(Ranking ranking) {
        Long competitionId = ranking.getCompetition().getId();
        Long memberId = ranking.getMember().getId();
        // checking the existence of competition

        Competition competition = getCompetitionById(competitionId);
        if(competition == null){
            throw new ResourceNotFoundException("Competition id " + competitionId + "not found");
        }
        // checking the existence of member
        Member member = memberService.getMemberById(memberId);
        if (member == null) {
            throw new ResourceNotFoundException("Member id " + memberId + "not found");
        }
        // Verifying that the member hasn't previously enrolled in this competition
        if(competition.getRanking().stream().anyMatch(ranking1 -> ranking1.getMember().getId().equals(memberId))){
            throw  new OperationException("Member id " + memberId + "has already enrolled in this competition");
        }

        // Checking that the competition start time is at least 24 hours away before verifying its start status
        if(competition.getStartTime().isBefore(competition.getStartTime().minusHours(24))) {
            throw new OperationException("Competition id " + competitionId + "Registration is closed.");
        }

        //Register a member in a competition
        return rankingService.addRanking(ranking);
    }
}
