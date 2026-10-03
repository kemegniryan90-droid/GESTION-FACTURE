package com.gestionfacture.servlet;

import com.gestionfacture.dao.ProduitDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/produits/supprimer")
public class SupprimerProduitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ProduitDAO produitDAO = new ProduitDAO();
        try {
            produitDAO.supprimer(id);
            response.sendRedirect(request.getContextPath() + "/produits");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la suppression du produit", e);
        }
    }
}
