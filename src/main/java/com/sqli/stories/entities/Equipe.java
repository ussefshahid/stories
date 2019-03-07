package com.sqli.stories.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "equipe")
public class Equipe implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "equipe",cascade = CascadeType.MERGE)
    private List<EquipeMember> members=new ArrayList<>();

    public Equipe() {
    }

    public Equipe(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EquipeMember> getMembers() {
        return members;
    }

    public void setMembers(List<EquipeMember> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipe equipe = (Equipe) o;
        return Objects.equals(id, equipe.id) &&
                Objects.equals(name, equipe.name) &&
                Objects.equals(members, equipe.members);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, members);
    }

}
