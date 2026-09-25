package com.gestionfacture.dao;

import com.gestionfacture.entite.LigneFacture;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LigneFactureDAO {

    public void ajouter(LigneFacture ligne) throws SQLException {
        String sql = "INSERT INTO ligne_facture (quantite, prix_unitaire, montant, id_facture, id_produit) VALUES (?, ?, ?, ?, ?)";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, ligne.getQuantite());
            ps.setDouble(2, ligne.getPrixUnitaire());
            ps.setDouble(3, ligne.getMontant());
            ps.setInt(4, ligne.getIdFacture());
            ps.setInt(5, ligne.getIdProduit());
            ps.executeUpdate();
        }
    }

    public List<LigneFacture> listerParFacture(int idFacture) throws SQLException {
        List<LigneFacture> lignes = new ArrayList<>();
        String sql = "SELECT * FROM ligne_facture WHERE id_facture = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, idFacture);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    LigneFacture l = new LigneFacture();
                    l.setId(rs.getInt("id"));
                    l.setQuantite(rs.getInt("quantite"));
                    l.setPrixUnitaire(rs.getDouble("prix_unitaire"));
                    l.setMontant(rs.getDouble("montant"));
                    l.setIdFacture(rs.getInt("id_facture"));
                    l.setIdProduit(rs.getInt("id_produit"));
                    lignes.add(l);
                }
            }
        }
        return lignes;
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM ligne_facture WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

