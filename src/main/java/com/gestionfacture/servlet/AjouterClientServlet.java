package com.gestionfacture.servlet;

import com.gestionfacture.dao.ClientDAO;
import com.gestionfacture.entite.Client;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/clients/ajouter")
public class AjouterClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/formulaireClient.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

        Client client = new Client();
        client.setNom(nom);
        client.setTelephone(telephone);
        client.setEmail(email);

        ClientDAO clientDAO = new ClientDAO();
        try {
            clientDAO.ajouter(client);
            response.sendRedirect("../clients");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'ajout du client", e);
        }
    }
}
