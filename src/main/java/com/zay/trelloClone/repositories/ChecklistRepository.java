package com.zay.trelloClone.repositories;

import com.zay.trelloClone.models.Checklist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChecklistRepository extends JpaRepository<Checklist, Integer> {
    public List<Checklist> findByCardId(Integer cardId);
}
