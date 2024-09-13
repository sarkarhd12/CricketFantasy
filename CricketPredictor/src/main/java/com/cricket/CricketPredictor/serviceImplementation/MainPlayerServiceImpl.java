package com.cricket.CricketPredictor.serviceImplementation;

import com.cricket.CricketPredictor.Exception.CountryNotFoundException;
import com.cricket.CricketPredictor.Exception.PlayerNotFoundException;
import com.cricket.CricketPredictor.dto.*;
import com.cricket.CricketPredictor.entity.Batsman;
import com.cricket.CricketPredictor.entity.Bowler;
import com.cricket.CricketPredictor.repository.BatsmanRepo;
import com.cricket.CricketPredictor.repository.BowlerRepo;
import com.cricket.CricketPredictor.service.MainPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MainPlayerServiceImpl implements MainPlayerService {

    @Autowired
    private final BowlerRepo bowlerRepository;

    @Autowired
    private final BatsmanRepo batsmanRepository;

    public MainPlayerServiceImpl(BowlerRepo bowlerRepository, BatsmanRepo batsmanRepository) {
        this.bowlerRepository = bowlerRepository;
        this.batsmanRepository = batsmanRepository;
    }

    @Override
    public List<PlayerDto> findByName(String name) {
        List<PlayerDto> players = new ArrayList<>();

        // Fetching bowlers and batsmen
        List<Bowler> bowlers = bowlerRepository.findByPlayerContainingIgnoreCase(name);
        List<Batsman> batsmen = batsmanRepository.findByPlayerContainingIgnoreCase(name);

        // Mapping to hold already processed players
        Map<String, PlayerDto> playerMap = new HashMap<>();

        // Process bowlers first
        for (Bowler bowler : bowlers) {
            String key = bowler.getPlayer() + "_" + bowler.getCountryName();
            PlayerDto dto = playerMap.getOrDefault(key, new PlayerDto());

            dto.setPlayer(bowler.getPlayer());
            dto.setSpan(bowler.getSpan());
            dto.setMatches(Optional.ofNullable(bowler.getMatches()).orElse(0));
            dto.setInnings(Optional.ofNullable(bowler.getInnings()).orElse(0));
            dto.setCountryName(bowler.getCountryName());
            dto.setTotalBall(Optional.ofNullable(bowler.getTotalBall()).orElse(0));
            dto.setRunsConcede(Optional.ofNullable(bowler.getRunsConcede()).orElse(0));
            dto.setTotalWickets(Optional.ofNullable(bowler.getTotalWickets()).orElse(0));
            dto.setBowlingAverage(Optional.ofNullable(bowler.getBowlingAverage()).orElse(0.0));
            dto.setBowlingEconomy(Optional.ofNullable(bowler.getBowlingEconomy()).orElse(0.0));
            dto.setBowlingStrickRate(Optional.ofNullable(bowler.getBowlingStrickRate()).orElse(0.0));
            dto.setFourWicket(Optional.ofNullable(bowler.getFourWicket()).orElse(0));
            dto.setFiveWicket(Optional.ofNullable(bowler.getFiveWicket()).orElse(0));

            playerMap.put(key, dto);
        }

        // Process batsmen and merge if exists
        for (Batsman batsman : batsmen) {
            String key = batsman.getPlayer() + "_" + batsman.getCountryName();
            PlayerDto dto = playerMap.getOrDefault(key, new PlayerDto());

            dto.setPlayer(batsman.getPlayer());
            dto.setSpan(batsman.getSpan());
            dto.setMatches(Optional.ofNullable(batsman.getMatches()).orElse(0));
            dto.setInnings(Optional.ofNullable(batsman.getInnings()).orElse(0));
            dto.setCountryName(batsman.getCountryName());
            dto.setNotOut(Optional.ofNullable(batsman.getNotOut()).orElse(0));
            dto.setRuns(Optional.ofNullable(batsman.getRuns()).orElse(0));
            dto.setHighestScore(Optional.ofNullable(batsman.getHighestScore()).orElse(String.valueOf(0)));
            dto.setBattingAverage(Optional.ofNullable(batsman.getBattingAverage()).orElse(0.0));
            dto.setBallFaced(Optional.ofNullable(batsman.getBallFaced()).orElse(0));
            dto.setBattingStrickRate(Optional.ofNullable(batsman.getBattingStrickRate()).orElse(0.0));
            dto.setHundreds(Optional.ofNullable(batsman.getHundreds()).orElse(0));
            dto.setFifties(Optional.ofNullable(batsman.getFifties()).orElse(0));
            dto.setDucks(Optional.ofNullable(batsman.getDucks()).orElse(0));

            playerMap.put(key, dto);
        }


        // Add all players from the map to the result list
        players.addAll(playerMap.values());
        if (players.isEmpty()) {
            throw new PlayerNotFoundException("No players found with the name: " + name);
        }

        return players;
    }


    @Override
    public Page<PlayerDto> getAllPlayers(Pageable pageable) {
        // Fetching bowlers and batsmen
        List<Bowler> bowlers = bowlerRepository.findAll();
        List<Batsman> batsmen = batsmanRepository.findAll();

        // Mapping to hold already processed players
        Map<String, PlayerDto> playerMap = new HashMap<>();

        // Process bowlers first
        for (Bowler bowler : bowlers) {
            String key = bowler.getPlayer() + "_" + bowler.getCountryName();
            PlayerDto dto = playerMap.getOrDefault(key, new PlayerDto());

            dto.setPlayer(bowler.getPlayer());
            dto.setSpan(bowler.getSpan());
            dto.setMatches(bowler.getMatches() != null ? bowler.getMatches() : 0);
            dto.setInnings(bowler.getInnings() != null ? bowler.getInnings() : 0);
            dto.setCountryName(bowler.getCountryName());
            dto.setTotalBall(bowler.getTotalBall() != null ? bowler.getTotalBall() : 0);
            dto.setRunsConcede(bowler.getRunsConcede() != null ? bowler.getRunsConcede() : 0);
            dto.setTotalWickets(bowler.getTotalWickets() != null ? bowler.getTotalWickets() : 0);
            dto.setBowlingAverage(bowler.getBowlingAverage() != null ? bowler.getBowlingAverage() : 0.0);
            dto.setBowlingEconomy(bowler.getBowlingEconomy() != null ? bowler.getBowlingEconomy() : 0.0);
            dto.setBowlingStrickRate(bowler.getBowlingStrickRate() != null ? bowler.getBowlingStrickRate() : 0.0);
            dto.setFourWicket(bowler.getFourWicket() != null ? bowler.getFourWicket() : 0);
            dto.setFiveWicket(bowler.getFiveWicket() != null ? bowler.getFiveWicket() : 0);

            playerMap.put(key, dto);
        }

        // Process batsmen and merge if exists
        for (Batsman batsman : batsmen) {
            String key = batsman.getPlayer() + "_" + batsman.getCountryName();
            PlayerDto dto = playerMap.getOrDefault(key, new PlayerDto());

            dto.setPlayer(batsman.getPlayer());
            dto.setSpan(batsman.getSpan());
            dto.setMatches(batsman.getMatches() != null ? batsman.getMatches() : 0);
            dto.setInnings(batsman.getInnings() != null ? batsman.getInnings() : 0);
            dto.setCountryName(batsman.getCountryName());
            dto.setNotOut(batsman.getNotOut() != null ? batsman.getNotOut() : 0);
            dto.setRuns(batsman.getRuns() != null ? batsman.getRuns() : 0);
            dto.setHighestScore(batsman.getHighestScore() != null ? batsman.getHighestScore() : "0");
            dto.setBattingAverage(batsman.getBattingAverage() != null ? batsman.getBattingAverage() : 0.0);
            dto.setBallFaced(batsman.getBallFaced() != null ? batsman.getBallFaced() : 0);
            dto.setBattingStrickRate(batsman.getBattingStrickRate() != null ? batsman.getBattingStrickRate() : 0.0);
            dto.setHundreds(batsman.getHundreds() != null ? batsman.getHundreds() : 0);
            dto.setFifties(batsman.getFifties() != null ? batsman.getFifties() : 0);
            dto.setDucks(batsman.getDucks() != null ? batsman.getDucks() : 0);

            playerMap.put(key, dto);
        }

        // Convert the map values to a list and paginate
        List<PlayerDto> playerList = new ArrayList<>(playerMap.values());
        int start = (int)pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), playerList.size());
        Page<PlayerDto> page = new PageImpl<>(playerList.subList(start, end), pageable, playerList.size());

        if (page.isEmpty()) {
            throw new PlayerNotFoundException("No players found!");
        }

        return page;
    }


    @Override
    public FantasyTeamDto createFantasyTeam(String countryName) {
        List<Bowler> bowlers = bowlerRepository.findByCountryNameContainingIgnoreCase(countryName);
        List<Batsman> batsmen = batsmanRepository.findByCountryNameContainingIgnoreCase(countryName);

        if (bowlers.isEmpty() || batsmen.isEmpty()) {
            throw new CountryNotFoundException("No players found for the country: " + countryName);
        }


        List<Batsman> topBatsmen = batsmen.stream()
                .map(batsman -> new ScoredBatsman(batsman, calculateBatsmanScore(batsman)))
                .sorted(Comparator.comparingDouble(ScoredBatsman::getScore).reversed())
                .limit(6)
                .map(ScoredBatsman::getBatsman)
                .collect(Collectors.toList());

        List<Bowler> topBowlers = bowlers.stream()
                .map(bowler -> new ScoredBowler(bowler, calculateBowlerScore(bowler)))
                .sorted(Comparator.comparingDouble(ScoredBowler::getScore).reversed())
                .limit(5)
                .map(ScoredBowler::getBowler)
                .collect(Collectors.toList());

        FantasyTeamDto fantasyTeam = new FantasyTeamDto();
        fantasyTeam.setBatsmen(topBatsmen);
        fantasyTeam.setBowlers(topBowlers);



        return fantasyTeam;
    }

    @Override
    public List<PlayerNameDto> SearchByName(String query) {
        List<Bowler> bowlers = bowlerRepository.findByPlayerContainingIgnoreCase(query);
        List<Batsman> batsmen = batsmanRepository.findByPlayerContainingIgnoreCase(query);

        // Use a map to combine players by name and country
        Map<String, PlayerNameDto> playerMap = new HashMap<>();

        // Convert bowlers to DTOs and add to the map
        bowlers.forEach(bowler -> {
            String key = bowler.getPlayer() + "-" + bowler.getCountryName();
            playerMap.putIfAbsent(key, new PlayerNameDto(bowler.getPlayer(), bowler.getCountryName()));
        });

        // Convert batsmen to DTOs and add to the map
        batsmen.forEach(batsman -> {
            String key = batsman.getPlayer() + "-" + batsman.getCountryName();
            playerMap.putIfAbsent(key, new PlayerNameDto(batsman.getPlayer(), batsman.getCountryName()));
        });

        // Convert the map values to a list
        List<PlayerNameDto> playerNameDtos = new ArrayList<>(playerMap.values());

        // Limit the number of suggestions
        // If you need to limit the number of results, uncomment the following line
        // playerNameDtos = playerNameDtos.stream().limit(10).collect(Collectors.toList());

        return playerNameDtos;
    }

    @Override
    public List<PlayerNameDto> findPlayerByCountryName(String countryName) {
        List<Bowler> bowlers = bowlerRepository.findByCountryNameContainingIgnoreCase(countryName);
        List<Batsman> batsmen = batsmanRepository.findByCountryNameContainingIgnoreCase(countryName);

        // Use a map to combine players by name and country
        Map<String, PlayerNameDto> playerMap = new HashMap<>();

        // Convert bowlers to DTOs and add to the map
        bowlers.forEach(bowler -> {
            String key = bowler.getPlayer() + "-" + bowler.getCountryName();
            playerMap.putIfAbsent(key, new PlayerNameDto(bowler.getPlayer(), bowler.getCountryName()));
        });

        // Convert batsmen to DTOs and add to the map
        batsmen.forEach(batsman -> {
            String key = batsman.getPlayer() + "-" + batsman.getCountryName();
            playerMap.putIfAbsent(key, new PlayerNameDto(batsman.getPlayer(), batsman.getCountryName()));
        });

        // Convert the map values to a list
        List<PlayerNameDto> playerNameDtos = new ArrayList<>(playerMap.values());

        // Limit the number of suggestions
        // If you need to limit the number of results, uncomment the following line
        // playerNameDtos = playerNameDtos.stream().limit(10).collect(Collectors.toList());

        return playerNameDtos;
    }

    @Override
    public Page<PlayerNameDto> findPlayerByCountryName(String countryName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Bowler> bowlerPage = bowlerRepository.findByCountryNameContainingIgnoreCase(countryName, pageable);
        Page<Batsman> batsmanPage = batsmanRepository.findByCountryNameContainingIgnoreCase(countryName, pageable);

        if (bowlerPage.isEmpty() && batsmanPage.isEmpty()) {
            throw new CountryNotFoundException("No teams found with the name: " + countryName);
        }

        // Combine and process the results as before
        Map<String, PlayerNameDto> playerMap = new HashMap<>();
        bowlerPage.forEach(bowler -> {
            String key = bowler.getPlayer() + "-" + bowler.getCountryName();
            playerMap.putIfAbsent(key, new PlayerNameDto(bowler.getPlayer(), bowler.getCountryName()));
        });

        batsmanPage.forEach(batsman -> {
            String key = batsman.getPlayer() + "-" + batsman.getCountryName();
            playerMap.putIfAbsent(key, new PlayerNameDto(batsman.getPlayer(), batsman.getCountryName()));
        });

        List<PlayerNameDto> playerNameDtos = new ArrayList<>(playerMap.values());

        return new PageImpl<>(playerNameDtos, pageable, playerNameDtos.size());
    }



    private double calculateBatsmanScore(Batsman batsman) {
        double score = 0;
        score += batsman.getRuns() != null ? batsman.getRuns() / 1000.0 : 0; // Runs
        score += batsman.getBattingAverage() != null ? batsman.getBattingAverage() / 50.0 : 0; // Average
        score += batsman.getHundreds() != null ? batsman.getHundreds() * 5.0 : 0; // Hundreds
        score += batsman.getFifties() != null ? batsman.getFifties() * 2.0 : 0; // Fifties
        score += batsman.getBallFaced() != null ? batsman.getBallFaced() / 100.0 : 0; // Balls Faced
        return score;
    }

    private double calculateBowlerScore(Bowler bowler) {
        double score = 0;
        score += bowler.getTotalWickets() != null ? bowler.getTotalWickets() * 3.0 : 0; // Wickets
        score += bowler.getRunsConcede() != null ? (300 - bowler.getRunsConcede()) / 10.0 : 0; // Runs Conceded
        score += bowler.getBowlingAverage() != null ? (30 - bowler.getBowlingAverage()) / 5.0 : 0; // Bowling Average
        score += bowler.getFourWicket() != null ? bowler.getFourWicket() * 2.0 : 0; // 4-Wicket Hauls
        score += bowler.getFiveWicket() != null ? bowler.getFiveWicket() * 5.0 : 0; // 5-Wicket Hauls
        return score;
    }

    private double calculatePlayerScore(PlayerDto player) {
        double score = 0;

        // Batting score calculation
        score += player.getRuns() != null ? player.getRuns() / 1000.0 : 0;
        score += player.getBattingAverage() != null ? player.getBattingAverage() / 50.0 : 0;
        score += player.getHundreds() != null ? player.getHundreds() * 5.0 : 0;
        score += player.getFifties() != null ? player.getFifties() * 2.0 : 0;
        score += player.getBallFaced() != null ? player.getBallFaced() / 100.0 : 0;

        // Bowling score calculation
        score += player.getTotalWickets() != null ? player.getTotalWickets() * 3.0 : 0;
        score += player.getRunsConcede() != null ? (300 - player.getRunsConcede()) / 10.0 : 0;
        score += player.getBowlingAverage() != null ? (30 - player.getBowlingAverage()) / 5.0 : 0;
        score += player.getFourWicket() != null ? player.getFourWicket() * 2.0 : 0;
        score += player.getFiveWicket() != null ? player.getFiveWicket() * 5.0 : 0;

        return score;
    }

private static class ScoredBatsman {
    private final Batsman batsman;
    private final double score;

    public ScoredBatsman(Batsman batsman, double score) {
        this.batsman = batsman;
        this.score = score;
    }

    public Batsman getBatsman() {
        return batsman;
    }

    public double getScore() {
        return score;
    }
}

private static class ScoredBowler {
    private final Bowler bowler;
    private final double score;

    public ScoredBowler(Bowler bowler, double score) {
        this.bowler = bowler;
        this.score = score;
    }
    public Bowler getBowler() {
        return bowler;
    }

    public double getScore() {
        return score;
    }
  }
}