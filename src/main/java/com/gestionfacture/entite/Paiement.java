package com.gestionfacture.entite;

import java.time.LocalDate;

public class Paiement {
    private int id;
    private double montant;
    private LocalDate date;
    private String mode;
    private int idFacture;

    public Paiement() {
    }

    public Paiement(int id, double montant, LocalDate date, String mode, int idFacture) {
        this.id = id;
        this.montant = montant;
        this.date = date;
        this.mode = mode;
        this.idFacture = idFacture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public int getIdFacture() {
        return idFacture;
    }

    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }
}
