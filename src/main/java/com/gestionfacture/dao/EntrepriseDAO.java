package com.gestionfacture.dao;

import com.gestionfacture.entite.Entreprise;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntrepriseDAO {

    public void ajouter(Entreprise entreprise) throws SQLException {
        String sql = "INSERT INTO entreprise (nom, adresse, telephone) VALUES (?, ?, ?)";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, entreprise.getNom());
            ps.setString(2, entreprise.getAdresse());
            ps.setString(3, entreprise.getTelephone());
            ps.executeUpdate();
        }
    }

    public List<Entreprise> listerTous() throws SQLException {
        List<Entreprise> entreprises = new ArrayList<>();
        String sql = "SELECT * FROM entreprise";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Entreprise e = new Entreprise();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setAdresse(rs.getString("adresse"));
                e.setTelephone(rs.getString("telephone"));
                entreprises.add(e);
            }
        }
        return entreprises;
    }

    public void modifier(Entreprise entreprise) throws SQLException {
        String sql = "UPDATE entreprise SET nom = ?, adresse = ?, telephone = ? WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, entreprise.getNom());
            ps.setString(2, entreprise.getAdresse());
            ps.setString(3, entreprise.getTelephone());
            ps.setInt(4, entreprise.getId());
            ps.executeUpdate();
        }
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM entreprise WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
