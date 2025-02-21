package com.karolaynmunoz.Model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Partida")
public class Partida implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_partida;

    @ManyToMany(mappedBy = "partides", fetch = FetchType.EAGER)
    private List<Equip> equips;

    public Partida() {}

    public Partida(List<Equip> equips) {
        this.equips = equips;
    }

    public int getId_partida() {
        return id_partida;
    }

    public void setId_partida(int id_partida) {
        this.id_partida = id_partida;
    }

    public List<Equip> getEquips() {
        return equips;
    }

    public void setEquips(List<Equip> equips) {
        this.equips = equips;
    }

    @Override
    public String toString() {
        StringBuilder equipsStr = new StringBuilder();
        if (equips != null) {
            for (Equip equip : equips) {
                if (equipsStr.length() > 0) {
                    equipsStr.append(", ");
                }
                equipsStr.append(equip.getNom_equip());
            }
        }
        return "Partida [id_partida=" + id_partida + ", equips=" + equipsStr + "]";
    }

}
