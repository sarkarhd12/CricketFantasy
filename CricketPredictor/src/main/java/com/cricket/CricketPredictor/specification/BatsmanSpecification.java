package com.cricket.CricketPredictor.specification;

import com.cricket.CricketPredictor.entity.Batsman;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BatsmanSpecification {

//    public static Specification<Batsman> byNameAndCountry(String playerName, String countryName,String role) {
//        return new Specification<Batsman>() {
//            @Override
//            public Predicate toPredicate(Root<Batsman> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                Predicate playerPredicate = criteriaBuilder.like(root.get("player"), "%" + playerName + "%");
//                Predicate countryPredicate = criteriaBuilder.equal(root.get("countryName"), countryName);
//                Predicate rolePredicate = criteriaBuilder.equal(root.get("role"), role);
//
//                return criteriaBuilder.and(playerPredicate, countryPredicate, rolePredicate);
//            }
//        };
//    }

    public static Specification<Batsman> byNameAndCountry(String playerName, String countryName, String role) {
        return new Specification<Batsman>() {
            @Override
            public Predicate toPredicate(Root<Batsman> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();

                // Add player name predicate if provided
                if (playerName != null && !playerName.isEmpty()) {
                    predicates.add(criteriaBuilder.like(root.get("player"), "%" + playerName + "%"));
                }

                // Add country name predicate if provided
                if (countryName != null && !countryName.isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("countryName"), countryName));
                }

                // Add role predicate if provided
                if (role != null && !role.isEmpty()) {
                    predicates.add(criteriaBuilder.equal(root.get("role"), role));
                }

                // Combine predicates using AND
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }
}
