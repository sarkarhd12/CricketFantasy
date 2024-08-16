package com.cricket.CricketPredictor.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Column(name = "player")
    private String player;

    @Column(name = "span")
    private String span;

    @Column(name = "matches")
    private Integer matches;

    @Column(name = "innings")
    private Integer innings;

    @Column(name="role")
    private String role;

    @Column(name = "country_name")
    private String countryName;
}
