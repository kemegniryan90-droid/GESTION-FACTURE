package com.gestionfacture.servlet;

import com.gestionfacture.dao.ClientDAO;
import com.gestionfacture.dao.FactureDAO;
import com.gestionfacture.entite.Client;
import com.gestionfacture.entite.Facture;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/factures")
public class ListerFacturesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FactureDAO factureDAO = new FactureDAO();
        ClientDAO clientDAO = new ClientDAO();
        try {
            List<Facture> factures = factureDAO.listerTous();
            List<Client> clients = clientDAO.listerTous();

            Map<Integer, String> nomsClients = new HashMap<>();
            for (Client c : clients) {
                nomsClients.put(c.getId(), c.getNom());
            }

            request.setAttribute("factures", factures);
            request.setAttribute("nomsClients", nomsClients);
            request.getRequestDispatcher("/WEB-INF/factures.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des factures", e);
        }
    }
}
