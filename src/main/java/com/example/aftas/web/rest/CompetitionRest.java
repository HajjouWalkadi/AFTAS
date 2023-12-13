package com.example.aftas.web.rest;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Ranking;
import com.example.aftas.dto.RankingRequestDTO;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.CompetitionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/competitions")
public class CompetitionRest {
    private final CompetitionService competitionService;

    public CompetitionRest(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getCompetitionById(@PathVariable Long id) {
        Competition competition = competitionService.getCompetitionById(id);
        if (competition == null) {
            return ResponseMessage.notFound("Competition not found");
        }else {
            return ResponseMessage.ok("Success", competition);
        }
    }

    @GetMapping
    public ResponseEntity getAllCompetitions() {
        List<Competition> competitions = competitionService.getAllCompetitions();
        if(competitions.isEmpty()) {
            return ResponseMessage.notFound("Competition not found");
        }else {
            return ResponseMessage.ok("Success", competitions);
        }
    }

    @PostMapping
    public ResponseEntity addCompetition(@Valid @RequestBody Competition competition) {
        Competition competition1 = competitionService.addCompetition(competition);
        if (competition1 == null) {
            return ResponseMessage.badRequest("Competition not created");
        }else {
            return ResponseMessage.created("Competition created successfully", competition1);
        }
    }


  /*  @GetMapping
    public ResponseEntity searchCompetition(@RequestBody String code) {
        List<Competition> competitions = competitionService.searchCompetition(code);
        if (competitions.isEmpty()) {
            return ResponseMessage.notFound("Competition not found");
        }else {
            return ResponseMessage.ok("Success", competitions);
        }
    }*/

    @PutMapping("/{id}")
    public ResponseEntity updateCompetition(@RequestBody Competition competition, @PathVariable Long id){
        Competition competition1 = competitionService.updateCompetition(competition, id);
        if (competition1 == null) {
            return ResponseMessage.badRequest("Competition not updated");
        }else {
            return ResponseMessage.created("Competition updated successfully", competition1);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompetition(@PathVariable Long id){
        Competition competition = competitionService.getCompetitionById(id);
        if (competition == null) {
            return ResponseMessage.notFound("Competition not found");
        }else {
            competitionService.deleteCompetition(id);
            return ResponseMessage.ok("Competition deleted successfully", null);
        }
    }

    public ResponseEntity rankingCompetitionResult(@Valid @RequestBody RankingRequestDTO rankingRequestDTO, @PathVariable Long id) {
        Ranking ranking = competitionService.rankingCompetitionResult(rankingRequestDTO.toRanking(), id);
        return ResponseMessage.ok("The competition results have been successfully logged", ranking);
    }
}