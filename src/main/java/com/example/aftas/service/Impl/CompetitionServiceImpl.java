package com.example.aftas.service.Impl;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;
import com.example.aftas.handler.exception.OperationException;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.repository.CompetitionRepository;
import com.example.aftas.repository.RankingRepository;
import com.example.aftas.service.CompetitionService;
import com.example.aftas.service.MemberService;
import com.example.aftas.service.RankingService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service

public class CompetitionServiceImpl implements CompetitionService {
    private final CompetitionRepository competitionRepository;
    private final RankingService rankingService;
    private final MemberService memberService;
    private final RankingRepository rankingRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, RankingService rankingService, MemberService memberService,
                                  RankingRepository rankingRepository) {
        this.competitionRepository = competitionRepository;
        this.rankingService = rankingService;
        this.memberService = memberService;
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Competition id " + id + " not found"));
    }

    @Override
    public Competition findByCode(String code) {
        return competitionRepository.findByCode(code).orElse(null);
    }

    @Override
    public Competition addCompetition(Competition competition) {

        // here i want to check that Every day there can be only one competition
        Competition competition1 = competitionRepository.findByDate(competition.getDate());
        if (competition1 != null) {
            throw new OperationException("There is an existing competition on " + competition.getDate());
        }

        // here i want to check that Competition start time must be before end time
        if (competition.getStartTime().isAfter(competition.getEndTime())) {
            throw new OperationException("It is required that the start time be before the end time.");
        }

        // here i want to generate a unique code for the competition from that date and location  pattern: ims-22-12-23, ims is the third first letters of the location
        String code = generateCode(competition.getLocation(), competition.getDate());
        competition.setCode(code);

        return competitionRepository.save(competition);

    }

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public List<Competition> getAllCompetitionsPaginated(Pageable pageable) {
        return competitionRepository.findAll(pageable).getContent();
    }




    public static String generateCode(String location, LocalDate date) {
        String locationCode = location.substring(0, 3).toLowerCase();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yy-MM-dd");
        String formattedDate = date.format(dateFormatter);

        return locationCode + "-" + formattedDate;
    }

   /* @Override
    public List<Competition> getAllCompetitions(Pageable pageable) {
        return competitionRepository.findAll(pageable).getContent();
    }*/


    @Override
    public Competition updateCompetition(Competition competition, Long id) {
        Competition competition1 = getCompetitionById(id);
        System.out.println(competition.getDate());
        System.out.println(competition1.getDate());

        if (!competition.getDate().equals(competition1.getDate())) {
            Competition competition2 = competitionRepository.findByDate(competition.getDate());
            if (competition2 != null) {
                throw new OperationException("There is already a competition on " + competition.getDate());
            }
        }
        competition1.setDate(competition.getDate());
        if (competition.getStartTime().isAfter(competition.getEndTime())) {
            throw new OperationException("It is required that the start time be before the end time.");
        }
        competition1.setStartTime(competition.getStartTime());
        competition1.setEndTime(competition.getEndTime());
        competition1.setLocation(competition.getLocation());
        if(competition.getLocation() != competition1.getLocation() || competition.getDate() != competition1.getDate()){
            String code = generateCode(competition.getLocation(), competition.getDate());
            competition1.setCode(code);
        }

        competition1.setAmount(competition.getAmount());
        return competitionRepository.save(competition1);
    }

    @Override
    public void deleteCompetition(Long id) {
        getCompetitionById(id);
        competitionRepository.deleteById(id);

    }

    @Override
    public Ranking registerMemberCompetition(Ranking ranking) {
        Long competitionId = ranking.getCompetition().getId();
        Long memberId = ranking.getMember().getId();
        // checking the existence of competition

        Competition competition = getCompetitionById(competitionId);
        if(competition == null){
            throw new ResourceNotFoundException("Competition with id " + competitionId + " not found");
        }
        // checking the existence of member
        Member member = memberService.getMemberById(memberId);
        if (member == null) {
            throw new ResourceNotFoundException("Member with id " + memberId + " not found");
        }
        // Verifying that the member hasn't previously enrolled in this competition
        if(competition.getRanking().stream().anyMatch(ranking1 -> ranking1.getMember().getId().equals(memberId))){
            throw  new OperationException("Member id " + memberId + " has already enrolled in this competition");
        }

        // Checking that the competition start time is at least 24 hours away before verifying its start status
        if(competition.getStartTime().isBefore(competition.getStartTime().minusHours(24))) {
            throw new OperationException("Competition id " + competitionId + " Registration is closed.");
        }

        //Register a member in a competition
        return rankingRepository.save(ranking);
    }
}
