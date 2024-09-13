package com.cricket.CricketPredictor.serviceImplementation;

import com.cricket.CricketPredictor.dto.GameResultDto;
import com.cricket.CricketPredictor.dto.MainDto;
import com.cricket.CricketPredictor.entity.Batsman;
import com.cricket.CricketPredictor.entity.Bowler;
import com.cricket.CricketPredictor.repository.BatsmanRepo;
import com.cricket.CricketPredictor.repository.BowlerRepo;
import com.cricket.CricketPredictor.service.GameWinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameWinnerServiceImpl implements GameWinnerService {

    @Autowired
    private final BowlerRepo bowlerRepository;

    @Autowired
    private final BatsmanRepo batsmanRepository;

    public GameWinnerServiceImpl(BowlerRepo bowlerRepository, BatsmanRepo batsmanRepository) {
        this.bowlerRepository = bowlerRepository;
        this.batsmanRepository = batsmanRepository;
    }

    @Override
    public GameResultDto determineWinner(List<MainDto> team1, List<MainDto> team2) {
        double team1BattingScore = calculateBattingScore(team1);
        double team1BowlingScore = calculateBowlingScore(team2);

        double team2BattingScore = calculateBattingScore(team2);
        double team2BowlingScore = calculateBowlingScore(team1);

        double team1PerformanceIndex = (team2BowlingScore > 0) ? team1BattingScore / team2BowlingScore : 0;
        double team2PerformanceIndex = (team1BowlingScore > 0) ? team2BattingScore / team1BowlingScore : 0;

        double totalPerformanceIndex = team1PerformanceIndex + team2PerformanceIndex;
        double team1WinningPercentage = (totalPerformanceIndex > 0) ? (team1PerformanceIndex / totalPerformanceIndex) * 100 : 50;
        double team2WinningPercentage = 100 - team1WinningPercentage;

        String winner = team1WinningPercentage > team2WinningPercentage ? "Team 1" : "Team 2";

        return new GameResultDto(winner, team1WinningPercentage, team2WinningPercentage);
    }

    private double calculateBattingScore(List<MainDto> team) {
        List<Batsman> batsmen = batsmanRepository.findByPlayerIn(
                team.stream().map(MainDto::getPlayer).collect(Collectors.toList())
        );

        double battingAverage = batsmen.stream().mapToDouble(Batsman::getBattingAverage).average().orElse(0.0);
        double strikeRate = batsmen.stream().mapToDouble(Batsman::getBattingStrickRate).average().orElse(0.0);
        System.out.println("Batting Average: " + battingAverage + ", Strike Rate: " + strikeRate);

        return (battingAverage * strikeRate) / 100;
    }

    private double calculateBowlingScore(List<MainDto> team) {
        List<Bowler> bowlers = bowlerRepository.findByPlayerIn(
                team.stream().map(MainDto::getPlayer).collect(Collectors.toList())
        );

        double economyRate = bowlers.stream().mapToDouble(Bowler::getBowlingEconomy).average().orElse(0.0);
        double bowlingAverage = bowlers.stream().mapToDouble(Bowler::getBowlingAverage).average().orElse(0.0);

        System.out.println("Economy Rate: " + economyRate + ", Bowling Average: " + bowlingAverage);

        double economyScore = (economyRate > 0) ? (1 / economyRate) : 0;
        double bowlingScore = (bowlingAverage > 0) ? (1 / bowlingAverage) : 0;

        System.out.println("Economy Score: " + economyScore + ", Bowling Score: " + bowlingScore);

        return economyScore + bowlingScore;
    }
}