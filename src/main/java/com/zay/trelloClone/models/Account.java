package com.zay.trelloClone.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Account extends MainModel {

    @Id
    private String username;

    @Column(nullable = false)
    private String name;

    @Column(nullable=false)
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;

    @Column(nullable = false)
    private Integer verified = 1;

    public Account(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Integer getVerified() {
        return verified;
    }

    public void setVerified(Integer verified) {
        this.verified = verified;
    }

//    public Set<Card> getCards() {
//        return cards;
//    }
//
//    public void setCards(Set<Card> cards) {
//        this.cards = cards;
//    }

}
