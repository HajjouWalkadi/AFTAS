package com.example.aftas.service.Impl;

import com.example.aftas.domain.Hunting;
import com.example.aftas.repository.HuntingRepository;
import com.example.aftas.service.HuntingService;
import org.springframework.stereotype.Service;

@Service
public class HuntingServiceImpl implements HuntingService {

    private final HuntingRepository huntingRepository;

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
    public Hunting updateHunting(Hunting hunting, Long id) {
        Hunting hunting1 = getHuntingById(id);
        hunting1.setNumberOfFish(hunting.getNumberOfFish());
        return huntingRepository.save(hunting1);
    }

    @Override
    public void deleteHunting(Long id) {
        huntingRepository.deleteById(id);
    }
}
