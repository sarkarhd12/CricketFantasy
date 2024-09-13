package com.cricket.CricketPredictor.dto;

import lombok.Data;


@Data
public class MainDto {
    private String player; // Use playerName for clarity
    private String countryName; // Renamed from role to countryName

    @Override
    public String toString() {
        return "MainDto{playerName='" + player + "', countryName='" + countryName + "'}";
    }
}