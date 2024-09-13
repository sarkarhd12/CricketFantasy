package com.cricket.CricketPredictor.dto;

import lombok.Data;

import java.util.List;


@Data
public class GameRequestDto {
    private List<MainDto> team1;
    private List<MainDto> team2;
}
