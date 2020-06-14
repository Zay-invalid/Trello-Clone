package com.zay.trelloClone.controllers;

import com.zay.trelloClone.exception.ResourceNotFoundException;
import com.zay.trelloClone.models.List;
import com.zay.trelloClone.repositories.ListRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/lists")
public class ListController {
    @Autowired
    private ListRepository listRepository;

    @GetMapping
    public java.util.List<List> getAll() {
        return listRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<List> getListById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        List list=  listRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("List not found on:: "+ id));
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public List create(@RequestBody List list) {
        return listRepository.saveAndFlush(list);
    }

    @DeleteMapping("{id}")
    public Map<String, Boolean> deleteById(@PathVariable Integer id) throws Exception {
        List list = listRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("List not found on:: " +id));
        listRepository.delete(list);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/{id}")
    public List update(@PathVariable Integer id, @RequestBody List list) {
        List oldList=listRepository.getOne(id);
        BeanUtils.copyProperties(list, oldList, "status" );
        return listRepository.saveAndFlush(oldList);
    }
}
