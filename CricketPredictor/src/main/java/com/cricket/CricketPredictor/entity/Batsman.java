package com.cricket.CricketPredictor.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "batsmandata")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Batsman extends Player{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long batsmanId;

    @Column(name="not_out")
    private Integer notOut;

    @Column(name="runs")
    private Integer runs;


    @Column(name = "highest_score")
    private String highestScore;

    @Column(name="ave")
    private Double battingAverage;

    @Column(name = "ball_faced")
    private Integer  ballFaced;

    @Column(name="strick_rate")
    private Double battingStrickRate;

    @Column(name="hundreds")
    private Integer hundreds;

    @Column(name="fifties")
    private Integer fifties;

    @Column(name="ducks")
    private Integer ducks;

}
