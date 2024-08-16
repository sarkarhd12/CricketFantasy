package com.cricket.CricketPredictor.service;

import com.cricket.CricketPredictor.dto.FantasyTeamDto;
import com.cricket.CricketPredictor.dto.MainDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MainPlayerService {
    List<MainDto> findByName(String name);

    public Page<MainDto> getAllPlayers(Pageable pageable);

    public FantasyTeamDto createFantasyTeam(String countryName);

}
