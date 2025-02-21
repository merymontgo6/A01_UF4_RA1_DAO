package com.karolaynmunoz.Model;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Personatge")
public class Personatge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_personatge;
    @Column
    private String nom_personatges;
   
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_rol",foreignKey=@ForeignKey(name="FK_PERSONATGE_ROL"),nullable=false)
    private Rol rol; 

    @OneToMany(mappedBy = "personatge", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Equip> equips;
    
    public Personatge() {}

    public Personatge(String nom, Rol rol) {
        this.nom_personatges = nom;
        this.rol = rol;
    }

    public Personatge(String nom) {
        this.nom_personatges = nom;
    }

    public int getId() {
        return id_personatge;
    }

    public void setId(int id) {
        this.id_personatge = id;
    }

    public String getNom() {
        return nom_personatges;
    }

    public void setNom(String nom) {
        this.nom_personatges = nom;
    }



    public Set<Equip> getEquips() {
        return equips;
    }

    public void setEquips(Set<Equip> equips) {
        this.equips = equips;
    } 

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "id_personatge=" + id_personatge +
                ", nom_personatges='" + nom_personatges + '\'' +
                ", rol=" + rol +
                ", equips=" + equips +
                '}';
    }
}
