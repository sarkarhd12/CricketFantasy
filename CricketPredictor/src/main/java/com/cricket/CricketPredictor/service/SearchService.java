package com.cricket.CricketPredictor.service;

import com.cricket.CricketPredictor.dto.PlayerSearchResultDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface SearchService {
    Page<PlayerSearchResultDTO> searchPlayers(String playerName, String countryName,String role, Pageable pageable);
    List<PlayerSearchResultDTO> filterPlayers(String filterBy, double value, String role);
}