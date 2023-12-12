package com.example.aftas.service.Impl;

import com.example.aftas.domain.Fish;
import com.example.aftas.domain.Level;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.repository.FishRepository;
import com.example.aftas.service.FishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishServiceImpl implements FishService {

    private FishRepository fishRepository;
    private LevelServiceImpl levelService;


    public FishServiceImpl(FishRepository fishRepository, LevelServiceImpl levelService) {
        this.fishRepository = fishRepository;
        this.levelService = levelService;
    }
    @Override
    public Fish getFishById(Long id) {
        return fishRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Fish id " + id + "not found"));
    }

    @Override
    public List<Fish> getAllFishes() {
        return fishRepository.findAll();
    }

    @Override
    public Fish addFish(Fish fish) {
        // check if fish name is not already exist
        if (fishRepository.findByName(fish.getName()) != null) {
            throw new ResourceNotFoundException("Fish name " + fish.getName() + " already exists");
        }
        // check if level is already exist
        if(levelService.getLevelById(fish.getLevel().getId()) == null) {
            throw new ResourceNotFoundException("Level id " + fish.getLevel().getId() + "not found");
        }
        return fishRepository.save(fish);
    }

    @Override
    public Fish updateFish(Fish fish, Long id) {
        Fish fish1 = getFishById(id);
        fish1.setName(fish.getName());
        fish1.setAverageWeight(fish.getAverageWeight());

        if (levelService.getLevelById(fish.getLevel().getId()) == null) {
            throw new ResourceNotFoundException("Level id " + fish.getLevel().getId() + "not found");
        }
        fish1.setLevel(fish.getLevel());
        return fishRepository.save(fish1);
    }

    @Override
    public void deleteFish(Long id) {
        fishRepository.deleteById(id);
    }
}
