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
import java.util.List;

@WebServlet("/clients")
public class ListerClientsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientDAO clientDAO = new ClientDAO();
        try {
            List<Client> clients = clientDAO.listerTous();
            request.setAttribute("clients", clients);
            request.getRequestDispatcher("/WEB-INF/clients.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des clients", e);
        }
    }
}
