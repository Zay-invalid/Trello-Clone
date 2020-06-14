package com.zay.trelloClone.controllers;

import com.zay.trelloClone.exception.ResourceNotFoundException;
import com.zay.trelloClone.models.Account;
import com.zay.trelloClone.models.Card;
import com.zay.trelloClone.models.Label;
import com.zay.trelloClone.repositories.AccountRepository;
import com.zay.trelloClone.repositories.CardRepository;
import com.zay.trelloClone.repositories.LabelRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LabelRepository labelRepository;

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

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteCard(@PathVariable(value = "id") Integer id)
            throws Exception {
        Card card = cardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Card not found on:: " + id));
        cardRepository.delete(card);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/{id}")
    public Card update(@PathVariable Integer id, @RequestBody Card card) throws Exception {
        Card oldCard = cardRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Card not found on:: " + id));
        BeanUtils.copyProperties(card, oldCard, "status" );
        return cardRepository.saveAndFlush(oldCard);
    }

    @PostMapping("/{id}/add-members")
    public Card addMembers(@RequestBody Set<String> username, @PathVariable Integer id) throws Exception{
        Card card =  cardRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Card not found on:: "+ id));
        Set<Account> members=card.getMembers();
        if (members == null) {
            members = new HashSet<>();
        }
        for (String oneUsername:username) {
            Account account = accountRepository.findById(oneUsername)
                    .orElseThrow(()->new ResourceNotFoundException("Account not found on:: "+ oneUsername));
            members.add(account);
        }
        card.setMembers(members);
        return cardRepository.saveAndFlush(card);
    }

    @PostMapping("/{id}/add-labels")
    public Card addLabels(@RequestBody Set<String> labelName, @PathVariable Integer id) throws Exception{
        Card card =  cardRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Card not found on:: "+ id));
        Set<Label> labels=card.getLabels();
        if (labels == null) {
            labels = new HashSet<>();
        }
        for (String oneLabelName: labelName) {
            Label label = labelRepository.findByName(oneLabelName)
                    .orElseThrow(()->new ResourceNotFoundException("The label name is not found on"));
            labels.add(label);
        }
        card.setLabels(labels);
        return cardRepository.saveAndFlush(card);
    }
}