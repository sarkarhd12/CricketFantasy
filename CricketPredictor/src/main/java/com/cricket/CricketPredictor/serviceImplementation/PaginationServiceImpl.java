package com.cricket.CricketPredictor.serviceImplementation;

import com.cricket.CricketPredictor.service.PaginationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PaginationServiceImpl implements PaginationService {
    @Override
    public Pageable createPagebale(int page, int size) {
        return PageRequest.of(page,size);
    }

    @Override
    public Pageable createPagebale(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return PageRequest.of(page, size, sort);
    }
}
