package com.example.aftas.service.Impl;

import com.example.aftas.domain.Fish;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.service.FishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishServiceImpl implements FishService {

    private FishRepository fishRepository;

    @Override
    public Fish getFishById(Long id) {
        return null;
    }

    @Override
    public List<Fish> getAllFishes() {
        return null;
    }

    @Override
    public Fish addFish(Fish fish) {
        return null;
    }

    @Override
    public Fish updateFish(Fish fish, Long id) {
        return null;
    }

    @Override
    public void deleteFish(Long id) {

    }
}
