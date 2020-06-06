package com.zay.trelloClone.controllers;

import com.zay.trelloClone.exception.ResourceNotFoundException;
import com.zay.trelloClone.models.Checklist;
import com.zay.trelloClone.repositories.ChecklistRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("checklists")
public class ChecklistController {
    @Autowired
    private ChecklistRepository checklistRepository;

    @GetMapping
    public List<Checklist> getAll(){
        return checklistRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Checklist> getListById(@PathVariable(value = "id") Integer id)
            throws Exception {
        Checklist checklist= checklistRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Checklist not found on:: "+ id));
        return ResponseEntity.ok().body(checklist);
    }

    @PostMapping
    public Checklist create(@RequestBody Checklist checklist) {
        return checklistRepository.saveAndFlush(checklist);
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteChecklist(@PathVariable(value = "id") Integer id) throws Exception {
        Checklist checklist = checklistRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Checklist not found on :: "+id));
        checklistRepository.delete(checklist);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/{id}")
    public Checklist update(@PathVariable Integer id, @RequestBody Checklist checklist) throws Exception {
        Checklist oldChecklist= checklistRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Checklist not found on:: "+ id));
        BeanUtils.copyProperties(checklist, oldChecklist, "status" );
        return checklistRepository.saveAndFlush(oldChecklist);
    }
}
