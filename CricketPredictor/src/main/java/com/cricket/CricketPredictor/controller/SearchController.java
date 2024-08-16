package com.cricket.CricketPredictor.controller;


import com.cricket.CricketPredictor.dto.PlayerSearchResultDTO;
import com.cricket.CricketPredictor.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private SearchService playerSearchService;

    @GetMapping("/search")
    public Page<PlayerSearchResultDTO> searchPlayers(
            @RequestParam(required = false) String playerName,
            @RequestParam(required = false) String countryName,
            @RequestParam(required = false) String role,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return playerSearchService.searchPlayers(playerName, countryName ,role, pageable);
    }

    @GetMapping("/filter")
    public List<PlayerSearchResultDTO> filterPlayers(
            @RequestParam String filterBy,
            @RequestParam double value,
            @RequestParam String role
    ) {
        return playerSearchService.filterPlayers(filterBy, value, role);
    }
}