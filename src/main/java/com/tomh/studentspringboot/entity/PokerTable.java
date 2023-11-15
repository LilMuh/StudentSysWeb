package com.tomh.studentspringboot.entity;

import java.util.ArrayList;
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

    public void getTable(PokerTable pt, Player player){
        pt.setId(1);
        List<Player> tablePlayers = new ArrayList<Player>();
        // Set bot as players
        tablePlayers.add(new Player(477561, "Alice", 1000, null, "prepare", 10));
        tablePlayers.add(new Player(35443, "Bob", 1000, null, "prepare", 10));
        tablePlayers.add(new Player(498161, "Jack", 1000, null, "prepare", 10));
        tablePlayers.add(new Player(5873, "May", 1000, null, "prepare", 10));
        // Add real player into table
        tablePlayers.add(player);
        pt.setPlayers(tablePlayers);
        // set dealer
        pt.setDealer(new Player(1005991919, "lilfist", 10, null, "dealer", 0));
        pt.setPot(0);
        pt.setBigBlind(10);
        pt.setSmallBlind(5);
        pt.setCurrentBet(10);
    }
}
