package com.example.aftas.dto;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Member;
import com.example.aftas.domain.Ranking;
import com.example.aftas.domain.RankingId;

public record MemberRegistrationRequestDTO(
        Long competitionId,
        Long memberId
) {

    public Ranking toRegister() {
        return Ranking.builder()
                .id(RankingId.builder().competitionId(competitionId).memberId(memberId).build())
                .member(Member.builder().id(memberId).build())
                .competition(Competition.builder().id(competitionId).build())
                .build();
    }
}
