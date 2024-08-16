package com.cricket.CricketPredictor.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bowlerdata")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bowler extends Player{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long bowlerId;

    @Column(name="ball_done")
    private Integer TotalBall;

    @Column(name="runs_concede")
    private Integer runsConcede;

    @Column(name = "wickets")
    private Integer totalWickets;

    @Column(name="average")
    private Double bowlingAverage;

    @Column(name = "economy")
    private Double bowlingEconomy;

    @Column(name="strick_rate")
    private Double bowlingStrickRate;

    @Column(name="four_wicket")
    private Integer fourWicket;

    @Column(name="five_wicket")
    private Integer fiveWicket;


}
