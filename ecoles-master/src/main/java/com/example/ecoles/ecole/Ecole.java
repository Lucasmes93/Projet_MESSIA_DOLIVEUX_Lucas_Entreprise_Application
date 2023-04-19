package com.example.ecoles.ecole;

import com.example.ecoles.classe.Classe;
import jakarta.persistence.*;

import java.util.HashSet;

import java.util.Set;

@Entity
public class Ecole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy="ecole",cascade = CascadeType.ALL)
    private Set<Classe> classes = new HashSet<>();
    private String name;

    public Set<Classe> getClasses() {
        return classes;
    }

    public void setClasses(Set<Classe> classes) {
        this.classes = classes;
        for(Classe c : classes) {
            c.setEcole(this);
        }
    }

    private String address;

    private String type;    // public ou privée

    public Ecole() { }
    public Ecole(String name, String address, String type ) {
        super();
        this.setName( name );
        this.setAddress( address );
        this.setType( type );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
