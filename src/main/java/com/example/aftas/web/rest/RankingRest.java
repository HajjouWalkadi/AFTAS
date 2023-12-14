package com.example.aftas.web.rest;

import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.RankingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ranking")
public class RankingRest {
    private final RankingService rankingService;

    public RankingRest(RankingService rankingService) {
        this.rankingService = rankingService;
    }
/*
    @GetMapping("/{id}")
    public ResponseEntity getRankingById(@PathVariable RankingId id) {
        Ranking ranking = rankingService.getRankingById(id);
        return ResponseMessage.ok("Success", ranking);
    }*/
/*

    @GetMapping

    public ResponseEntity getRankingsByCompetitionIdAndMemberId(@PathVariable Long competitionId, @PathVariable Long memberId) {
        Ranking ranking = rankingService.getRankingByCompetitionIdAndMemberId(competitionId, memberId);
        return ResponseMessage.ok("Success", ranking);
    }
*/


    @PostMapping
    public ResponseEntity addRanking(@Valid @RequestBody Ranking ranking){
        Ranking ranking1 = rankingService.addRanking(ranking);
        if (ranking1 == null) {
            return ResponseMessage.badRequest("Failed to add ranking");
        }else {
            return ResponseMessage.created("Ranking added successfully", ranking1);
        }

    }

    @PutMapping("/{id}")

    public ResponseEntity updateRanking(@RequestBody Ranking ranking, @PathVariable RankingId id) {
        Ranking ranking1 = rankingService.updateRanking(ranking, id);
        return ResponseMessage.ok("Success", ranking1);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity deleteRanking(@PathVariable RankingId id) {
        rankingService.deleteRanking(id);
        return ResponseMessage.ok("Ranking deleted successfully", null);
    }
}
