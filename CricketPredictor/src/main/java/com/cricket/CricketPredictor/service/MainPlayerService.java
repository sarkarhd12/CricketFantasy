package com.cricket.CricketPredictor.service;

import com.cricket.CricketPredictor.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MainPlayerService {
    List<PlayerDto> findByName(String name);

     Page<PlayerDto> getAllPlayers(Pageable pageable);

     FantasyTeamDto createFantasyTeam(String countryName);

    List<PlayerNameDto> SearchByName(String name);

    List<PlayerNameDto> findPlayerByCountryName(String countryName);

    Page<PlayerNameDto> findPlayerByCountryName(String countryName, int page, int size);


}
