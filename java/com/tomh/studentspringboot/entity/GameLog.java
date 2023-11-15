package com.tomh.studentspringboot.entity;

import javax.persistence.*;

@Entity
@Table(name="essay")
public class GameLog {

    @Column(name = "gameid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int gameId;
    @Column(name = "actions")
    private String actions;
    @Column(name = "results")
    private String results;

    public GameLog() {
    }

    public GameLog(int gameId, String actions, String results) {
        this.gameId = gameId;
        this.actions = actions;
        this.results = results;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
