package com.tomh.studentspringboot.service;

import com.tomh.studentspringboot.entity.*;
import com.tomh.studentspringboot.mapper.TexasDao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TexasService {

    @Resource
    private TexasDao texasDao;

    public PokerTable startGame(Player player) {
        Deck deck = new Deck();
        deck.shuffle();
        // 洗牌完成
        PokerTable pt = new PokerTable();
        pt.getTable(pt, player);
        // 分桌完成
        for (Player eachPlayer : pt.getPlayers()) {
            Hand hand = new Hand(Arrays.asList(deck.dealCard(), deck.dealCard()), "Royal flush");
            eachPlayer.setHand(hand);
        }
        // 玩家发牌完成
        pt.setCommunityCards(Arrays.asList(deck.dealCard(),deck.dealCard(),deck.dealCard(),deck.dealCard(),deck.dealCard()));
        // 牌桌发牌完成
        return pt;
    }
}