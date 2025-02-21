package com.karolaynmunoz.Model;
import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Equip")
public class Equip implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_equip;

    @Column
    private String nom_equip;

    @ManyToOne
    @JoinColumn(name = "id_personatge")
    private Personatge personatge;

    @ManyToMany
    @JoinTable( name = "equip_partida",
        joinColumns = @JoinColumn(name = "id_equip", foreignKey = @ForeignKey(name = "FK_EQUIP_PARTIDA_EQUIP"),nullable = false),
        inverseJoinColumns = @JoinColumn(name = "id_partida", foreignKey = @ForeignKey(name = "FK_EQUIP_PARTIDA_PARTIDA"), nullable = false))
    private Set<Partida> partides;

    public Equip() {}

    public Equip(String nom_equip, Personatge id_personatge) {
        this.nom_equip = nom_equip;
        this.personatge = id_personatge;
    }

    public Equip(String nom_equip) {
        this.nom_equip = nom_equip;
    }

    public int getId_equip() {
        return id_equip;
    }

    public void setId_equip(int id_equip) {
        this.id_equip = id_equip;
    }

    public void setNom_equip(String nom_equip) {
        this.nom_equip = nom_equip;
    }

    public String getNom_equip() {
        return nom_equip;
    }

    public Personatge getId_Personatge() {
        return personatge;
    }

    public void setIdPersonatge(Personatge id_personatge) {
        this.personatge = id_personatge;
    }

    public Set<Partida> getPartides() {
        return partides;
    }

    public void setPartidas(Set<Partida> partides) {
        this.partides = partides;
    }

    @Override // Override toString method fet per chat, partides dava errors
    public String toString() {
        StringBuilder partidesStr = new StringBuilder();
        if (partides != null) {
            for (Partida partida : partides) {
                if (partidesStr.length() > 0) {
                    partidesStr.append(", ");
                }
                partidesStr.append(partida.getId_partida());
            }
        }
        return "Equip{" +
                "id_equip=" + id_equip +
                ", nom_equip='" + nom_equip + '\'' +
                ", personatge=" + (personatge != null ? personatge.getNom() : "null") +
                ", partides=" + partidesStr +
                '}';
    }
}