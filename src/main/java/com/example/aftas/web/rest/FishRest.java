package com.example.aftas.web.rest;

import com.example.aftas.domain.Fish;
import com.example.aftas.dto.FishRequestDTO;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.FishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/fish")
@RequiredArgsConstructor // create constructor for all the final properties
public class FishRest {
    private final FishService fishService;


    @GetMapping("/{id}")
    public ResponseEntity getFishById(@PathVariable Long id) {
        Fish fish = fishService.getFishById(id);
        if(fish == null) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            return ResponseMessage.ok("Success",fish);
        }
    }

    @GetMapping
    public ResponseEntity getAllFishes() {
        List<Fish> fishList = fishService.getAllFishes();
        if (fishList.isEmpty()) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            return ResponseMessage.ok("Success", fishList);
        }
    }

    @PostMapping
    public ResponseEntity addFish(@Valid @RequestBody FishRequestDTO fishRequestDTO) {
        Fish fish1 = fishService.addFish(fishRequestDTO.toFish());
        if (fish1 == null) {
            return ResponseMessage.badRequest("Failed to create Fish.");
        }else {
            return ResponseMessage.created("Fish added successfully", fish1);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateFish(@Valid @RequestBody FishRequestDTO fishRequestDTO, @PathVariable Long id) {
        Fish fish = fishService.updateFish(fishRequestDTO.toFish(), id);
        if (fish == null) {
            return ResponseMessage.badRequest("Failed to update Fish.");
        }else {
            return ResponseMessage.created("Fish created successfully", fish);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteFish(@PathVariable Long id) {
        Fish fish = fishService.getFishById(id);
        if (fish == null) {
            return ResponseMessage.notFound("Fish not found");
        }else {
            fishService.deleteFish(id);
            return ResponseMessage.ok("Fish deleted successfully",null);
        }
    }
}
