package com.cricket.CricketPredictor.dto;

import com.cricket.CricketPredictor.entity.Batsman;
import com.cricket.CricketPredictor.entity.Bowler;
import lombok.Data;

import java.util.List;

@Data
public class FantasyTeamDto {
    private List<Batsman> batsmen;
    private List<Bowler> bowlers;
}
