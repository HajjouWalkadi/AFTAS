package com.example.aftas.service.Impl;

import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Hunting;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.HuntingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuntingServiceImpl implements HuntingService {

    private HuntingRepository huntingRepository;
    private MemberServiceImpl memberService;
    private CompetitionServiceImpl competitionService;


    public HuntingServiceImpl(HuntingRepository huntingRepository) {
        this.huntingRepository = huntingRepository;
    }

    @Override
    public Hunting getHuntingById(Long id) {
        return huntingRepository.findById(id).orElse(null);
    }

    @Override
    public Hunting addHunting(Hunting hunting) {
        return huntingRepository.save(hunting);
    }

    @Override
    public List<Hunting> getHuntingsByCompetition(Long competitionId) {
        return null;
    }

    @Override
    public List<Hunting> getHuntingsByCompetitionAndMember(Long competitionId, Long memberId) {
        return null;
    }


    @Override
    public void deleteHunting(Long id) {
        huntingRepository.deleteById(id);
    }
}
