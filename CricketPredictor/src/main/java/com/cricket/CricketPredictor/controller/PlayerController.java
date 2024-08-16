package com.cricket.CricketPredictor.controller;

import com.cricket.CricketPredictor.dto.FantasyTeamDto;
import com.cricket.CricketPredictor.dto.MainDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface PlayerController {

    ResponseEntity findByName(String name);

    ResponseEntity<Page<MainDto>> getAllPlayers(Pageable pageable);

    ResponseEntity<FantasyTeamDto> getFantasyTeam(String countryName);

}
