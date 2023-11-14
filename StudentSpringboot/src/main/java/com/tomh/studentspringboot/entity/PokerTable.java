package com.tomh.studentspringboot.entity;

import java.util.List;

public class PokerTable {
    private int id;
    private List<Player> players;
    private List<Card> communityCards;
    private int pot;
    private Player dealer;
    private int smallBlind;
    private int bigBlind;
    private int currentBet;

    public PokerTable() {
    }

    public PokerTable(int id, List<Player> players, List<Card> communityCards, int pot, Player dealer, int smallBlind, int bigBlind, int currentBet) {
        this.id = id;
        this.players = players;
        this.communityCards = communityCards;
        this.pot = pot;
        this.dealer = dealer;
        this.smallBlind = smallBlind;
        this.bigBlind = bigBlind;
        this.currentBet = currentBet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Card> getCommunityCards() {
        return communityCards;
    }

    public void setCommunityCards(List<Card> communityCards) {
        this.communityCards = communityCards;
    }

    public int getPot() {
        return pot;
    }

    public void setPot(int pot) {
        this.pot = pot;
    }

    public Player getDealer() {
        return dealer;
    }

    public void setDealer(Player dealer) {
        this.dealer = dealer;
    }

    public int getSmallBlind() {
        return smallBlind;
    }

    public void setSmallBlind(int smallBlind) {
        this.smallBlind = smallBlind;
    }

    public int getBigBlind() {
        return bigBlind;
    }

    public void setBigBlind(int bigBlind) {
        this.bigBlind = bigBlind;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }
}
