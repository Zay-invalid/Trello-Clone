package com.zay.trelloClone.repositories;

import com.zay.trelloClone.models.Checklist;
import com.zay.trelloClone.models.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label, Integer> {
}
