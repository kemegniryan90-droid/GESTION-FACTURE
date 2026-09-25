package com.gestionfacture.dao;

import com.gestionfacture.entite.Paiement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaiementDAO {

    public void ajouter(Paiement paiement) throws SQLException {
        String sql = "INSERT INTO paiement (montant, date_paiement, mode, id_facture) VALUES (?, ?, ?, ?)";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setDouble(1, paiement.getMontant());
            ps.setDate(2, Date.valueOf(paiement.getDate()));
            ps.setString(3, paiement.getMode());
            ps.setInt(4, paiement.getIdFacture());
            ps.executeUpdate();
        }
    }

    public List<Paiement> listerParFacture(int idFacture) throws SQLException {
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiement WHERE id_facture = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, idFacture);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Paiement p = new Paiement();
                    p.setId(rs.getInt("id"));
                    p.setMontant(rs.getDouble("montant"));
                    p.setDate(rs.getDate("date_paiement").toLocalDate());
                    p.setMode(rs.getString("mode"));
                    p.setIdFacture(rs.getInt("id_facture"));
                    paiements.add(p);
                }
            }
        }
        return paiements;
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM paiement WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
