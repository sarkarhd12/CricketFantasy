package com.cricket.CricketPredictor.specification;


import com.cricket.CricketPredictor.entity.Bowler;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class BowlerSpecification {

    public static Specification<Bowler> byNameAndCountry(String playerName, String countryName) {
        return new Specification<Bowler>() {
            @Override
            public Predicate toPredicate(Root<Bowler> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Predicate playerPredicate = criteriaBuilder.like(root.get("player"), "%" + playerName + "%");
                Predicate countryPredicate = criteriaBuilder.equal(root.get("countryName"), countryName);
                return criteriaBuilder.and(playerPredicate, countryPredicate);
            }
        };
    }
}
