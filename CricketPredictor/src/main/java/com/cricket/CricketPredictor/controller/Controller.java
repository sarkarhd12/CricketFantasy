package com.cricket.CricketPredictor.controller;

import com.cricket.CricketPredictor.dto.FantasyTeamDto;
import com.cricket.CricketPredictor.dto.MainDto;
import com.cricket.CricketPredictor.service.MainPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller implements PlayerController{

    @Autowired
    private final MainPlayerService mainPlayerService;


    public Controller(MainPlayerService mainPlayerService) {
        this.mainPlayerService = mainPlayerService;
    }

    @Override
    @GetMapping("/find")
    public ResponseEntity findByName(String name) {
        List<MainDto> players = mainPlayerService.findByName(name);
        if (players.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(players);
    }

    @Override
    @GetMapping("/getall")
    public ResponseEntity<Page<MainDto>> getAllPlayers(Pageable pageable) {
        Page<MainDto> players = mainPlayerService.getAllPlayers( pageable);
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

}
