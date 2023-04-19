package com.example.ecoles.classe;

import com.example.ecoles.ecole.Ecole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Classe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ecole_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Ecole ecole;
    private String niveau;

    private String name;

    private Integer nbEleves;

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
    public Ecole getEcole() {
        return ecole;
    }

    public void setEcole(Ecole ecole) {
        this.ecole = ecole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String name() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setIdClasses(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNbEleves() {
        return nbEleves;
    }

    public void setNbEleves(Integer nbEleves) {
        this.nbEleves = nbEleves;
    }
}
