package com.cricket.CricketPredictor.repository;

import com.cricket.CricketPredictor.entity.Batsman;
import com.cricket.CricketPredictor.entity.Bowler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatsmanRepo extends JpaRepository<Batsman, Long>, JpaSpecificationExecutor<Batsman> {
   public List<Batsman> findByPlayerContainingIgnoreCase(String name);

   public Page<Batsman> findAll(Pageable pageable);

   List<Batsman> findAll(Specification<Batsman> specification);

   List<Batsman> findByCountryNameContainingIgnoreCase(String countryName);


}
