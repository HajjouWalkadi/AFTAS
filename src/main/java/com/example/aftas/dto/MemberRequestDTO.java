package com.example.aftas.dto;

import com.example.aftas.domain.Member;
import com.example.aftas.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Date;

public record MemberRequestDTO(
        @NotNull(message = "Name is required")
        @Size(min = 3, max = 35, message = "Name must be between 3 and 35 characters")
        String name,

        @NotNull(message = "Family name is required")
        @Size(min = 3, max = 35, message = "Family name must be between 3 and 35 characters")
        String familyName,

        @NotNull(message = "Nationality is required")
        String nationality,

        LocalDate accessionDate,

        IdentityDocumentType identityDocumentType,
        String identityNumber
) {

    public Member toMember(){
        return Member.builder()
                .name(name)
                .familyName(familyName)
                .accessionDate(accessionDate)
                .nationality(nationality)
                .identityDocumentType(identityDocumentType)
                .identityNumber(identityNumber)
                .build();
    }
}
