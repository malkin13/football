package com.motosport.football.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

    private Integer scoreTeamOne;

    private Integer scoreTeamTwo;

    private Integer stateTeamOne;

    private Integer stateTeamTwo;

    @Override
    public String toString() {
        return "Game{id=" + id + "team1=" + team1
                + "team2=" + team2
                + "teamOneScore=" + scoreTeamOne
                + "teamTwoScore=" + scoreTeamTwo
                + "teamOneState=" + stateTeamOne
                + "teamTwoState=" + stateTeamTwo
                + "  }";
    }

}
