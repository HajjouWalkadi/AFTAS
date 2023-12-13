package com.example.aftas.dto;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;

public record MemberRegistrationRequestDTO(
        Long competitionId,
        Long memberId
) {

    public Ranking toRanking() {
        return Ranking.builder()
                .member(Member.builder().id(memberId).build())
                .competition(Competition.builder().id(competitionId).build())
                .build();
    }
}
