package com.gestionfacture.dao;

import com.gestionfacture.entite.Facture;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FactureDAO {

    public int ajouter(Facture facture) throws SQLException {
        String sql = "INSERT INTO facture (numero, date_facture, total, id_client, id_entreprise) VALUES (?, ?, ?, ?, ?)";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, facture.getNumero());
            ps.setDate(2, Date.valueOf(facture.getDate()));
            ps.setDouble(3, facture.getTotal());
            ps.setInt(4, facture.getIdClient());
            ps.setInt(5, facture.getIdEntreprise());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    public Facture recupererParId(int id) throws SQLException {
        String sql = "SELECT * FROM facture WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Facture f = new Facture();
                    f.setId(rs.getInt("id"));
                    f.setNumero(rs.getString("numero"));
                    f.setDate(rs.getDate("date_facture").toLocalDate());
                    f.setTotal(rs.getDouble("total"));
                    f.setIdClient(rs.getInt("id_client"));
                    f.setIdEntreprise(rs.getInt("id_entreprise"));
                    return f;
                }
            }
        }
        return null;
    }

    public List<Facture> listerTous() throws SQLException {
        List<Facture> factures = new ArrayList<>();
        String sql = "SELECT * FROM facture";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Facture f = new Facture();
                f.setId(rs.getInt("id"));
                f.setNumero(rs.getString("numero"));
                f.setDate(rs.getDate("date_facture").toLocalDate());
                f.setTotal(rs.getDouble("total"));
                f.setIdClient(rs.getInt("id_client"));
                f.setIdEntreprise(rs.getInt("id_entreprise"));
                factures.add(f);
            }
        }
        return factures;
    }

    public void modifier(Facture facture) throws SQLException {
        String sql = "UPDATE facture SET numero = ?, date_facture = ?, total = ?, id_client = ?, id_entreprise = ? WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, facture.getNumero());
            ps.setDate(2, Date.valueOf(facture.getDate()));
            ps.setDouble(3, facture.getTotal());
            ps.setInt(4, facture.getIdClient());
            ps.setInt(5, facture.getIdEntreprise());
            ps.setInt(6, facture.getId());
            ps.executeUpdate();
        }
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM facture WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
