package com.example.aftas.repository;

import com.example.aftas.domain.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HuntingRepository extends JpaRepository<Hunting, Long> {
  //List<Hunting  findById(Long id);
}
