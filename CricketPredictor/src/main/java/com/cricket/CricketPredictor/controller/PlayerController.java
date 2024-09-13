package com.cricket.CricketPredictor.controller;

import com.cricket.CricketPredictor.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PlayerController {

    ResponseEntity findByName(String name);

    ResponseEntity<Page<PlayerDto>> getAllPlayers(Pageable pageable);

    ResponseEntity<FantasyTeamDto> getFantasyTeam(String countryName);

    ResponseEntity<List<PlayerNameDto>> searchByPlayer(@RequestParam String query);

    ResponseEntity<List<PlayerNameDto>> searchByCountry(String country);

    ResponseEntity<Page<PlayerNameDto>> searchByCountry(
           String country,
             int page,
             int size);

    ResponseEntity<GameResultDto> determineWinner( GameRequestDto gameRequest);
}
