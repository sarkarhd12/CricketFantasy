package com.cricket.CricketPredictor.serviceImplementation;

import com.cricket.CricketPredictor.dto.PlayerSearchResultDTO;
import com.cricket.CricketPredictor.entity.Batsman;
import com.cricket.CricketPredictor.entity.Bowler;
import com.cricket.CricketPredictor.repository.BatsmanRepo;
import com.cricket.CricketPredictor.repository.BowlerRepo;
import com.cricket.CricketPredictor.service.SearchService;
import com.cricket.CricketPredictor.specification.BatsmanSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SearchServiceImplementation implements SearchService {


    @Autowired
    private BatsmanRepo batsmanRepository;

    @Autowired
    private BowlerRepo bowlerRepository;

    @Override
    public Page<PlayerSearchResultDTO> searchPlayers(String playerName, String countryName, String role, Pageable pageable) {
      final Specification<Batsman> batsmanSpecification = BatsmanSpecification.byNameAndCountry(playerName,countryName, role);

      final List<Batsman> batsmen = batsmanRepository.findAll(batsmanSpecification);
        List<PlayerSearchResultDTO> playerSearchResultDTOS = batsmen.stream()
                .map(batsman -> new PlayerSearchResultDTO("Batsman", batsman))
                .collect(Collectors.toList());

        // Create a Page<PlayerSearchResultDTO> directly from the list
        return new PageImpl<>(playerSearchResultDTOS, pageable, playerSearchResultDTOS.size());

    }

    @Override
    public List<PlayerSearchResultDTO> filterPlayers(String filterBy, double value, String role) {
        List<PlayerSearchResultDTO> filteredPlayers;

        if ("Batsman".equalsIgnoreCase(role)) {
            List<Batsman> batsmen = batsmanRepository.findAll();
            filteredPlayers = batsmen.stream()
                    .filter(batsman -> filterConditionMet(batsman, filterBy, value))
                    .map(batsman -> new PlayerSearchResultDTO("Batsman", batsman))
                    .collect(Collectors.toList());

        } else if ("Bowler".equalsIgnoreCase(role)) {
            List<Bowler> bowlers = bowlerRepository.findAll();
            filteredPlayers = bowlers.stream()
                    .filter(bowler -> filterConditionMet(bowler, filterBy, value))
                    .map(bowler -> new PlayerSearchResultDTO("Bowler", bowler))
                    .collect(Collectors.toList());

        } else {
            filteredPlayers = List.of();
        }

        return filteredPlayers;
    }

    private boolean filterConditionMet(Batsman batsman, String filterBy, double value) {
        switch (filterBy.toLowerCase()) {
            case "average":
                return batsman.getBattingAverage() >= value;
            case "runs":
                return batsman.getRuns() >= value;
            case "matches":
                return batsman.getMatches() >= value;
            case "strikerate":
                return batsman.getBattingStrickRate() >= value;
            case "fifties":
                return batsman.getFifties() >= value;
            case "hundreds":
                return batsman.getHundreds() >= value;
            case "ducks":
                return batsman.getDucks() <= value;
            default:
                return false;
        }
    }

    private boolean filterConditionMet(Bowler bowler, String filterBy, double value) {
        switch (filterBy.toLowerCase()) {
            case "average":
                return bowler.getBowlingAverage() <= value;
            case "wickets":
                return bowler.getTotalWickets() >= value;
            case "matches":
                return bowler.getMatches() >= value;
            case "strikerate":
                return bowler.getBowlingStrickRate() <= value;
            case "economy":
                return bowler.getBowlingEconomy() <= value;
            case "fourwicket":
                return bowler.getFourWicket() >= value;
            case "fivewicket":
                return bowler.getFiveWicket() >= value;
            case "runsconcede":
                return bowler.getRunsConcede() <= value;
            default:
                return false;
        }
    }
}