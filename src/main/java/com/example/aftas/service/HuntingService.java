package com.example.aftas.service;

import com.example.aftas.domain.Hunting;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface HuntingService {
    Hunting getHuntingById(Long id);
    Hunting addHunting(Hunting hunting);
    Hunting updateHunting(Hunting hunting, Long id);
    void deleteHunting(Long id);
}
