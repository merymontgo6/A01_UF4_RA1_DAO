package com.karolaynmunoz.Model;

import java.io.Serializable;
import java.util.Set;
public class Rol implements Serializable {
    private int id_rol;
    private String nom_rol;
    private Set<Personatge> personatges;

    public Rol() {}

    public Rol(String nom) {
        this.nom_rol = nom;
    }

    public Rol(String nom_rol, Set<Personatge> personatges) {
        this.nom_rol = nom_rol;
        this.personatges = personatges;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id) {
        this.id_rol = id;
    }

    public String getNom_rol() {
        return nom_rol;
    }

    public void setNom_rol(String nom) {
        this.nom_rol = nom;
    }

    public Set<Personatge> getPersonatges() {
        return personatges;
    }

    public void setPersonatges(Set<Personatge> personatges) {
        this.personatges = personatges;
    }

    @Override
    public String toString() {
        return "Rol [id=" + id_rol + ", nom=" + nom_rol +"]";
    }
}
