package com.example.aftas.dto;

import com.example.aftas.domain.Competition;
import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Hunting;
import com.example.aftas.domain.Member;

public record HuntingRequestDTO(
        Long competitionId,
        Long memberId,
        Long fishedId
) {
    public Hunting toHunting() {
        return Hunting.builder()
                .competition(Competition.builder().id(competitionId).build())
                .member(Member.builder().id(memberId).build())
                .fish(Fish.builder().id(fishedId).build())
                .build();
    }
}
