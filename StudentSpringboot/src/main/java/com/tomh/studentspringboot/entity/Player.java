package com.tomh.studentspringboot.entity;

import javax.persistence.*;

@Table(name="essay")
public class Player {

    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "chips")
    private int chips;
    @Column(name = "hand")
    private String hand;
    @Column(name = "status")
    private String status;
    @Column(name = "currentBet")
    private int currentBet;

// Initialization

    public Player() {
    }

    public Player(int id, String name, int chips, String hand, String status, int currentBet) {
        this.id = id;
        this.name = name;
        this.chips = chips;
        this.hand = hand;
        this.status = status;
        this.currentBet = currentBet;
    }

// Getters and Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChips() {
        return chips;
    }

    public void setChips(int chips) {
        this.chips = chips;
    }

    public String getHand() {
        return hand;
    }

    public void setHand(String hand) {
        this.hand = hand;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }
}
