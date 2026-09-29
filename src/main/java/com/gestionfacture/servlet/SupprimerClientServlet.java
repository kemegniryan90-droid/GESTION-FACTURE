package com.gestionfacture.servlet;

import com.gestionfacture.dao.ClientDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/clients/supprimer")
public class SupprimerClientServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ClientDAO clientDAO = new ClientDAO();
        try {
            clientDAO.supprimer(id);
            response.sendRedirect("../clients");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la suppression du client", e);
        }
    }
}
