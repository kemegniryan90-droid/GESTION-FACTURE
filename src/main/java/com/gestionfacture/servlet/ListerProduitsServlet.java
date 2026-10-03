package com.gestionfacture.servlet;

import com.gestionfacture.dao.ProduitDAO;
import com.gestionfacture.entite.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/produits")
public class ListerProduitsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProduitDAO produitDAO = new ProduitDAO();
        try {
            List<Produit> produits = produitDAO.listerTous();
            request.setAttribute("produits", produits);
            request.getRequestDispatcher("/WEB-INF/produits.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des produits", e);
        }
    }
}
