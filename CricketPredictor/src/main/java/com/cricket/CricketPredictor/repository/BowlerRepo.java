package com.cricket.CricketPredictor.repository;


import com.cricket.CricketPredictor.entity.Batsman;
import com.cricket.CricketPredictor.entity.Bowler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;


public interface BowlerRepo extends JpaRepository<Bowler ,Long>, JpaSpecificationExecutor<Bowler> {

    public List<Bowler> findByPlayerContainingIgnoreCase(String name);

    public Page<Bowler> findAll(org.springframework.data.domain.Pageable pageable);

    List<Bowler> findAll(Specification<Bowler> specification);

    List<Bowler> findByCountryNameContainingIgnoreCase(String countryName);

}
