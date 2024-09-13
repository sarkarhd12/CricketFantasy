package com.cricket.CricketPredictor.dto;

import lombok.Data;

@Data
public class GameResultDto {
    private String winner;
    private double team1WinningPercentage;
    private double team2WinningPercentage;

    public GameResultDto(String winner, double team1WinningPercentage, double team2WinningPercentage) {
        this.winner = winner;
        this.team1WinningPercentage = team1WinningPercentage;
        this.team2WinningPercentage = team2WinningPercentage;
    }
}
