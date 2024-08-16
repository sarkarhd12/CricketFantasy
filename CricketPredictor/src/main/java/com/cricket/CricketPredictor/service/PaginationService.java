package com.cricket.CricketPredictor.service;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

@Service
public interface PaginationService {
     Pageable createPagebale(int page, int size);

     Pageable createPagebale(int page, int size, String sortBy, String direction);
}
