package com.mazebank.entities;

import java.util.Date;
import com.mazebank.utils.*;

public class OffreEmbauche implements Comparable<OffreEmbauche> {
    
    private int id;
     private String poste;
     private Date dateEmbauche;
     private float salaire;
     private int duree;
    
    public OffreEmbauche() {}

    public OffreEmbauche(int id, String poste, Date dateEmbauche, float salaire, int duree) {
        this.id = id;
        this.poste = poste;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
        this.duree = duree;
    }

    public OffreEmbauche(String poste, Date dateEmbauche, float salaire, int duree) {
        this.poste = poste;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
        this.duree = duree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
    
    public float getSalaire() {
        return salaire;
    }

    public void setSalaire(float salaire) {
        this.salaire = salaire;
    }
    
    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    
    @Override
    public String toString() {
        return "OffreEmbauche : " +
                "id=" + id
                 + ", Poste=" + poste
                 + ", DateEmbauche=" + dateEmbauche
                 + ", Salaire=" + salaire
                 + ", Duree=" + duree
                ;
    }
    
    @Override
    public int compareTo(OffreEmbauche offreEmbauche) {
        switch (Statics.compareVar) {
            case "Poste":
                 return this.getPoste().compareTo(offreEmbauche.getPoste());
            case "DateEmbauche":
                DateUtils.compareDates(this.getDateEmbauche(), offreEmbauche.getDateEmbauche());
            case "Salaire":
                return Float.compare(this.getSalaire(), offreEmbauche.getSalaire());
            case "Duree":
                return Integer.compare(this.getDuree(), offreEmbauche.getDuree());
            
            default:
                return 0;
        }
    }
    
}