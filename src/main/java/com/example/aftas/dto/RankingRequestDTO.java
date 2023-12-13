package com.example.aftas.dto;

import com.example.aftas.domain.Ranking;

public record RankingRequestDTO(
        Long id,
        int score
) {
    public Ranking toRanking() {
        return Ranking.builder()
                .id(id)
                .score(score)
                .build();
    }
}
