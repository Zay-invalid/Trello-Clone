package com.zay.trelloClone.repositories;

import com.zay.trelloClone.models.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository <List, Integer> {
    public java.util.List<List> findByPositionGreaterThanEqual(Integer position);

    public java.util.List<List> findByTitleContaining(String searchTerm);
}
