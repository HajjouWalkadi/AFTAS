package com.example.aftas.service.Impl;

import com.example.aftas.domain.Level;
import com.example.aftas.handler.exception.OperationException;
import com.example.aftas.handler.exception.ResourceNotFoundException;
import com.example.aftas.repository.LevelRepository;
import com.example.aftas.service.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class LevelServiceImpl implements LevelService {
    private final LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    @Override
    public Level getLevelById(Long id) {
        return levelRepository.findById(id).orElseThrow(() ->new ResourceNotFoundException("Level id " + id + "not found"));
    }

    @Override
    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    @Override
    public Level addLevel(Level level) {
        Level level1 = levelRepository.findAll().stream().max((l1, l2) -> l1.getPoints() > l2.getPoints() ? 1 : -1).orElse(null);
        if (level1 != null) {
            if (level.getPoints() <= level1.getPoints()) {
                throw new OperationException("Points must be greater than " + level1.getPoints());
            }
        }
        return levelRepository.save(level);
    }


    @Override
    public Level updateLevel(Level level, Long id) {
        Level level1 = getLevelById(id);
        level1.setDescription(level.getDescription());
        // check if point is greater than previous level and less than next level
        List<Level> levels = levelRepository.findAll();
        int index = id.intValue()-1;
        if (index == 0) {
            if (level.getPoints() >= levels.get((index+1)).getPoints()) {
                throw new OperationException(("Point must be less than " + levels.get((index+1)).getPoints()));
            }
        } else if (index == levels.size()-1) {
            if(level.getPoints() <= levels.get((index-1)).getPoints()) {
                throw new OperationException("Point must be greater than " + levels.get((index-1)).getPoints());
            }
        }else {
            if(level.getPoints() >= levels.get((index+1)).getPoints()) {
                throw new OperationException("Point must be less than " + levels.get((index+1)).getPoints());
            }
        }
        level1.setPoints(level.getPoints());
        return levelRepository.save(level1);
    }

    @Override
    public void deleteLevel(Long id) {
        levelRepository.deleteById(id);
    }
}
