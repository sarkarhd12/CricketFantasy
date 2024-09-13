package com.cricket.CricketPredictor.service;

import com.cricket.CricketPredictor.dto.GameResultDto;
import com.cricket.CricketPredictor.dto.MainDto;


import java.util.List;

public interface GameWinnerService {
    GameResultDto determineWinner(List<MainDto> team1, List<MainDto> team2);
}
