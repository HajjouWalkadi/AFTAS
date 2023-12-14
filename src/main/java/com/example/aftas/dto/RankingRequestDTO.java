package com.example.aftas.dto;

import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;

public record RankingRequestDTO(
        RankingId id,
        int score
) {

    public Ranking toRanking() {
        return Ranking.builder()
                .id(id)
                .score(score)
                .build();
    }
}
