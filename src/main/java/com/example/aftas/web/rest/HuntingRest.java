package com.example.aftas.web.rest;

import com.example.aftas.domain.Hunting;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.HuntingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/hunting")
public class HuntingRest {
    private final HuntingService huntingService;


    public HuntingRest(HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getHuntingById(@PathVariable Long id){
        Hunting hunting = huntingService.getHuntingById(id);
        if (hunting == null) {
            return ResponseMessage.notFound("hunting not found");
        }else {
            return ResponseMessage.ok("Success",hunting);
        }
    }


    @PostMapping
    public ResponseEntity addHunting(@Valid @RequestBody Hunting hunting){
        Hunting hunting1 = huntingService.addHunting(hunting);
        if (hunting1 == null) {
            return ResponseMessage.badRequest("Hunting not created");
        }else {
            return ResponseMessage.created("Hunting created successfully",hunting1);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity updateHunting(@RequestBody Hunting hunting, @PathVariable Long id) {
        Hunting hunting1 = huntingService.updateHunting(hunting,id);
        if (hunting1 == null) {
            return ResponseMessage.badRequest("Hunting not updated");
        }else {
            return ResponseMessage.created("Hunting updated successfully",hunting1);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHunting(@PathVariable Long id) {
        Hunting hunting = huntingService.getHuntingById(id);
        if (hunting == null) {
            return ResponseMessage.notFound("Hunting not found");
        }else {
            huntingService.deleteHunting(id);
            return ResponseMessage.ok("Hunting deleted successfully", hunting);
        }
    }












}
