package com.zay.trelloClone.repositories;

import com.zay.trelloClone.models.Label;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LabelRepository extends JpaRepository<Label, Integer> {

    Optional <Label> findByName(String name);
}
