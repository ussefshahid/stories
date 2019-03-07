package com.sqli.stories.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class EquipeMember implements Serializable {
    @Id
    private Equipe equipe;
    @EmbeddedId
    private Member member;
    @EmbeddedId
    private Role role;
    private LocalDate dateEntree;
    private LocalDate dateSortie;
}
