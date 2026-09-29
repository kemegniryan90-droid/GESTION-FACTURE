package com.gestionfacture.dao;

import com.gestionfacture.entite.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    public Client recupererParId(int id) throws SQLException {
        String sql = "SELECT * FROM client WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Client c = new Client();
                    c.setId(rs.getInt("id"));
                    c.setNom(rs.getString("nom"));
                    c.setTelephone(rs.getString("telephone"));
                    c.setEmail(rs.getString("email"));
                    return c;
                }
            }
        }
        return null;
    }

    public void ajouter(Client client) throws SQLException {
        String sql = "INSERT INTO client (nom, telephone, email) VALUES (?, ?, ?)";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getTelephone());
            ps.setString(3, client.getEmail());
            ps.executeUpdate();
        }
    }

    public List<Client> listerTous() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Client c = new Client();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setTelephone(rs.getString("telephone"));
                c.setEmail(rs.getString("email"));
                clients.add(c);
            }
        }
        return clients;
    }

    public void modifier(Client client) throws SQLException {
        String sql = "UPDATE client SET nom = ?, telephone = ?, email = ? WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setString(1, client.getNom());
            ps.setString(2, client.getTelephone());
            ps.setString(3, client.getEmail());
            ps.setInt(4, client.getId());
            ps.executeUpdate();
        }
    }

    public void supprimer(int id) throws SQLException {
        String sql = "DELETE FROM client WHERE id = ?";
        try (Connection cnx = ConnexionBD.getConnection();
             PreparedStatement ps = cnx.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}

