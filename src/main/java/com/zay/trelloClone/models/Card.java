package com.zay.trelloClone.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "card")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Card extends MainModel{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private String title;
    private String description;
    @Column (name = "due_date")
    private Date dueDate;

    @Column(nullable = false)
    private Integer position;

    @Column(nullable = false)
    private Integer list_id;

    private Byte status = 1;

    @ManyToMany(cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY)
    @JsonIgnoreProperties("cards")
    @JoinTable(name = "card_label",
                joinColumns ={@JoinColumn (name = "card_id")},
                inverseJoinColumns ={@JoinColumn (name = "label_id")})
    private Set<Label> labels = new HashSet<>();


    @ManyToMany(mappedBy = "cards")
    @JsonIgnoreProperties("cards")
    private Set<Account> member = new HashSet<Account>();


    @OneToMany(mappedBy = "card",
                fetch = FetchType.LAZY,
                cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("card")
    private Set<Checklist>checklists = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY,
                optional = false)
    @JsonIgnoreProperties("cards")
    @JoinColumn(name = "list_id",
                insertable = false,
                updatable = false,
                nullable = false)
    private List list;

    public Card() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getList_id() {
        return list_id;
    }

    public void setList_id(Integer list_id) {
        this.list_id = list_id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Set<Account> getMember() {
        return member;
    }

    public void setMember(Set<Account> member) {
        this.member = member;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Set<Label> getLabels() {
        return labels;
    }

    public void setLabels(Set<Label> labels) {
        this.labels = labels;
    }

    public Set<Checklist> getChecklists() {
        return checklists;
    }

    public void setChecklists(Set<Checklist> checklists) {
        this.checklists = checklists;
    }

}
