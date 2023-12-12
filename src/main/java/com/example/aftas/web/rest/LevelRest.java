package com.example.aftas.web.rest;

import com.example.aftas.domain.Level;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.LevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/levels")
public class LevelRest {
    private final LevelService levelService;

    public LevelRest(LevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getLevelById(@PathVariable Long id) {
        Level level = levelService.getLevelById(id);
        if (level == null) {
            return ResponseMessage.notFound("Level not found");
        }else {
            return ResponseMessage.ok("Success", level);
        }
    }

    @PostMapping
    public ResponseEntity addLevel(@RequestBody Level level) {
    Level level1 = levelService.addLevel(level);
    if (level == null) {
        return ResponseMessage.badRequest("Level not created");
    }else {
        return ResponseMessage.created("Level created successfully", level1);
    }
    }
}
