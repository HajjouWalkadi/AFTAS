package com.example.aftas.web.rest;

import com.example.aftas.domain.Hunting;
import com.example.aftas.dto.HuntingRequestDTO;
import com.example.aftas.handler.response.ResponseMessage;
import com.example.aftas.service.HuntingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hunting")
public class HuntingRest {
    private final HuntingService huntingService;


    public HuntingRest(HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity getHuntingById(@PathVariable Long id){
            return ResponseMessage.ok("Success",huntingService.getHuntingById(id));
        }


    @PostMapping
    public ResponseEntity addHunting(@RequestBody HuntingRequestDTO huntingRequestDTO){
        Hunting hunting1 = huntingService.addHunting(huntingRequestDTO.toHunting());
        if (hunting1 == null) {
            return ResponseMessage.badRequest("Hunting not added");
        }else {
            return ResponseMessage.created("Hunting added successfully",hunting1);
        }
    }

   public ResponseEntity getHuntingsByCompetition(@PathVariable Long competitionId){
       //List<Hunting> huntings = huntingService.
               return null;
   }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHunting(@PathVariable Long id) {
            huntingService.deleteHunting(id);
            return ResponseMessage.ok("Hunting deleted successfully", null);
    }












}
