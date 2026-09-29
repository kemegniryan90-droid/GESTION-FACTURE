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

@WebServlet("/clients/modifier")
public class ModifierClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ClientDAO clientDAO = new ClientDAO();
        try {
            Client client = clientDAO.recupererParId(id);
            request.setAttribute("client", client);
            request.getRequestDispatcher("/WEB-INF/modifierClient.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération du client", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");

        Client client = new Client();
        client.setId(id);
        client.setNom(nom);
        client.setTelephone(telephone);
        client.setEmail(email);

        ClientDAO clientDAO = new ClientDAO();
        try {
            clientDAO.modifier(client);
            response.sendRedirect("../clients");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la modification du client", e);
        }
    }
}
