package com.gestionfacture.dao;

import com.gestionfacture.entite.Produit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {

    public void ajouter(Produit produit) throws SQLException {
        String sql = "INSERT INTO produit (designation, prix_unitaire, stock) VALUES (?, ?, ?)";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, produit.getDesignation());
            ps.setDouble(2, produit.getPrixUnitaire());
            ps.setInt(3, produit.getStock());
            ps.executeUpdate();
        }
    }

    public Produit recupererParId(int id) throws SQLException {
        String sql = "SELECT * FROM produit WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Produit p = new Produit();
                    p.setId(rs.getInt("id"));
                    p.setDesignation(rs.getString("designation"));
                    p.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                    p.setStock(rs.getInt("stock"));
                    return p;
                }
            }
        }
        return null;
    }

    public List<Produit> listerTous() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Produit p = new Produit();
                p.setId(rs.getInt("id"));
                p.setDesignation(rs.getString("designation"));
                p.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                p.setStock(rs.getInt("stock"));
                produits.add(p);
            }
        }
        return produits;
    }

    public void modifier(Produit produit) throws SQLException {
        String sql = "UPDATE produit SET designation = ?, prix_unitaire = ?, stock = ? WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, produit.getDesignation());
            ps.setDouble(2, produit.getPrixUnitaire());
            ps.setInt(3, produit.getStock());
            ps.setInt(4, produit.getId());
            ps.executeUpdate();
        }
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM produit WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
