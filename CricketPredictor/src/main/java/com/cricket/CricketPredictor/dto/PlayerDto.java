package com.cricket.CricketPredictor.dto;

import lombok.Data;

@Data
public class PlayerDto {

        private String player;
        private String span;
        private Integer matches;
        private Integer innings;
        private String countryName;

        // Batting statistics
        private Integer notOut;
        private Integer runs;
        private String highestScore;
        private Double battingAverage;
        private Integer ballFaced;
        private Double battingStrickRate;
        private Integer hundreds;
        private Integer fifties;
        private Integer ducks;



        // Bowling statistics
        private Integer totalBall;
        private Integer runsConcede;
        private Integer totalWickets;
        private Double bowlingAverage;
        private Double bowlingEconomy;
        private Double bowlingStrickRate;
        private Integer fourWicket;
        private Integer fiveWicket;
    }

