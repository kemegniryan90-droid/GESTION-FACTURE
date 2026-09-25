package com.gestionfacture.entite;

public class LigneFacture {
    private int id;
    private int quantite;
    private double prixUnitaire;
    private double montant;
    private int idFacture;
    private int idProduit;

    public LigneFacture() {
    }

    public LigneFacture(int id, int quantite, double prixUnitaire, double montant, int idFacture, int idProduit) {
        this.id = id;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
        this.montant = montant;
        this.idFacture = idFacture;
        this.idProduit = idProduit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
}
