package com.cricket.CricketPredictor.controller;

import com.cricket.CricketPredictor.Exception.CountryNotFoundException;
import com.cricket.CricketPredictor.dto.*;
import com.cricket.CricketPredictor.service.GameWinnerService;
import com.cricket.CricketPredictor.service.MainPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin(origins = "http://localhost:3000")
public class PlayerControllerImplementation implements PlayerController {

    @Autowired
    private final MainPlayerService mainPlayerService;

    @Autowired
    private GameWinnerService gameWinnerService;


    public PlayerControllerImplementation(MainPlayerService mainPlayerService) {
        this.mainPlayerService = mainPlayerService;
    }

    @Override
    @GetMapping("/find")
    public ResponseEntity findByName(@RequestParam String name) {
        List<PlayerDto> players = mainPlayerService.findByName(name);
        if (players.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(players);
    }

    @Override
    @GetMapping("/getall")
    public ResponseEntity<Page<PlayerDto>> getAllPlayers(Pageable pageable) {
        Page<PlayerDto> players = mainPlayerService.getAllPlayers(pageable);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @Override
    @GetMapping("/team")
    public ResponseEntity<FantasyTeamDto> getFantasyTeam(
            @RequestParam String countryName
    ) {
        FantasyTeamDto fantasyTeam = mainPlayerService.createFantasyTeam(countryName);
        return new ResponseEntity<>(fantasyTeam, HttpStatus.OK);
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<PlayerNameDto>> searchByPlayer(
            @RequestParam String query) {
        List<PlayerNameDto> suggestions = mainPlayerService.SearchByName(query);
        if (suggestions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(suggestions);
    }

    @Override
    @GetMapping("/findBy")
    public ResponseEntity<List<PlayerNameDto>> searchByCountry(@RequestParam String country) {
        List<PlayerNameDto> suggestions = mainPlayerService.findPlayerByCountryName(country);
        if (suggestions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(suggestions);
    }

    @Override
    @GetMapping("/findByCountry")
    public ResponseEntity<Page<PlayerNameDto>> searchByCountry(
            @RequestParam String country,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        try {
            Page<PlayerNameDto> suggestions = mainPlayerService.findPlayerByCountryName(country, page, size);
            return ResponseEntity.ok(suggestions);
        } catch (CountryNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Or a custom error message
        }
    }

    @Override
    @PostMapping("/determineWinner")
    public ResponseEntity<GameResultDto> determineWinner(@RequestBody GameRequestDto gameRequest) {
        List<MainDto> team1 = gameRequest.getTeam1();
        List<MainDto> team2 = gameRequest.getTeam2();
        team1.forEach(System.out::println);
        team2.forEach(System.out::println);

        GameResultDto result = gameWinnerService.determineWinner(team1, team2);

        return ResponseEntity.ok(result);
    }

}
