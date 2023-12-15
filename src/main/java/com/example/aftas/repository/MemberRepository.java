package com.example.aftas.repository;

import com.example.aftas.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query(value =
    "SELECT * FROM member where reference_number = :search " +
    "OR name LIKE %:search% OR family_name LIKE %:search%", nativeQuery = true)
    List<Member> searchByMemberNameOrNumber(@Param("search") String search);
}
