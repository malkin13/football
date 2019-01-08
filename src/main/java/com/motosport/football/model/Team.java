package com.motosport.football.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Getter
@Setter
@Table(name = "team", schema = "public")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @NotNull
    @ManyToOne(targetEntity = Group.class)
    @JoinColumn(name = "group_id")
    private Group group;

    @Override
    public String toString() {
        return "Team{id=" + id + "name=" + name
                + "group=" + group
                + "  }";
    }

}
