package com.tomh.studentspringboot.entity;

import java.util.List;

public class Hand {
    private List<Card> cards;
    private String handRank;

    public Hand() {
    }

    public Hand(List<Card> cards, String handRank) {
        this.cards = cards;
        this.handRank = handRank;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getHandRank() {
        return handRank;
    }

    public void setHandRank(String handRank) {
        this.handRank = handRank;
    }
}
