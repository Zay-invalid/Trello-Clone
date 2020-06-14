package com.zay.trelloClone.repositories;

import com.zay.trelloClone.models.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListRepository extends JpaRepository <List, Integer> {
}
