package com.zay.trelloClone.controllers;

import com.zay.trelloClone.exception.ResourceNotFoundException;
import com.zay.trelloClone.models.Account;
import com.zay.trelloClone.models.Card;
import com.zay.trelloClone.repositories.CardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @GetMapping
    public List<Card> list() {
        return cardRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable(value = "id") Integer id)
            throws ResourceNotFoundException {
        Card card =  cardRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Card not found on:: "+ id));
        return ResponseEntity.ok().body(card);
    }

    @PostMapping
    public Card create(@RequestBody Card card) {
        return cardRepository.saveAndFlush(card);
    }

    @DeleteMapping
    public Map<String, Boolean> deleteCard(@PathVariable(value = "id") Integer id)
            throws Exception {
        Card card = cardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Card not found on:: " + id));
        cardRepository.delete(card);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/{username}")
    public Card update(@PathVariable Integer id, @RequestBody Card card) throws Exception {
        Card oldCard = cardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Card not found on:: " + id));
        BeanUtils.copyProperties(card, oldCard, "status" );
        return cardRepository.saveAndFlush(oldCard);
    }
}