package com.zay.trelloClone.controllers;

import com.zay.trelloClone.exception.ResourceNotFoundException;
import com.zay.trelloClone.models.Label;
import com.zay.trelloClone.repositories.LabelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("labels")
public class LabelController {
    @Autowired
    private LabelRepository labelRepository;

    @GetMapping
    public List<Label> getAll() {
        return labelRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Label> getLabelById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        Label label=labelRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Label not found on:: "+ id));
        return ResponseEntity.ok().body(label);
    }

    @PostMapping
    public Label create(@RequestBody Label label){
        return labelRepository.saveAndFlush(label);
    }

    @PutMapping("{id}")
    public Label update(@PathVariable Integer id, @RequestBody Label label) {
        Label oldLabel = labelRepository.getOne(id);
        BeanUtils.copyProperties(label, oldLabel, "status");
        return labelRepository.saveAndFlush(oldLabel);
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> delete(@PathVariable(value = "id") Integer id) throws Exception {
        Label label = labelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Label not found on :: "+ id));
        labelRepository.delete(label);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

