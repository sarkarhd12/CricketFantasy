package com.cricket.CricketPredictor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerSearchResultDTO {
    private String playerType;
    private Object playerDetails;
}
