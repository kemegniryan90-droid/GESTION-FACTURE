package com.gestionfacture.entite;

import java.time.LocalDate;

public class Facture {
    private int id;
    private String numero;
    private LocalDate date;
    private double total;
    private int idClient;
    private int idEntreprise;

    public Facture() {
    }

    public Facture(int id, String numero, LocalDate date, double total, int idClient, int idEntreprise) {
        this.id = id;
        this.numero = numero;
        this.date = date;
        this.total = total;
        this.idClient = idClient;
        this.idEntreprise = idEntreprise;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(int idEntreprise) {
        this.idEntreprise = idEntreprise;
    }
}
