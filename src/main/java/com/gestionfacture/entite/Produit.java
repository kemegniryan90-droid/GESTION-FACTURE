package com.gestionfacture.entite;

public class Produit {
    private int id;
    private String designation;
    private double prixUnitaire;
    private int stock;

    public Produit() {
    }

    public Produit(int id, String designation, double prixUnitaire, int stock) {
        this.id = id;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
