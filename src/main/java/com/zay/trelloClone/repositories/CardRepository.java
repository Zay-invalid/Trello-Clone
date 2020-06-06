package com.zay.trelloClone.repositories;

import com.zay.trelloClone.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Integer> {
}
