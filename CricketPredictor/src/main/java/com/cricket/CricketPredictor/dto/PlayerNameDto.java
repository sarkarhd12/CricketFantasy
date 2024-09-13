package com.cricket.CricketPredictor.dto;

import lombok.Data;

@Data
public class PlayerNameDto {
    private String player;
    private String countryName;

    public PlayerNameDto(String player, String countryName) {
        this.player = player;
        this.countryName = countryName;
    }


}
