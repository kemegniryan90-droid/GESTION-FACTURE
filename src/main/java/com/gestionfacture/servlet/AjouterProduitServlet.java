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

@WebServlet("/produits/ajouter")
public class AjouterProduitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/formulaireProduit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String designation = request.getParameter("designation");
        double prixUnitaire = Double.parseDouble(request.getParameter("prixUnitaire"));
        int stock = Integer.parseInt(request.getParameter("stock"));

        Produit produit = new Produit();
        produit.setDesignation(designation);
        produit.setPrixUnitaire(prixUnitaire);
        produit.setStock(stock);

        try {
            new ProduitDAO().ajouter(produit);
            response.sendRedirect(request.getContextPath() + "/produits");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'ajout du produit", e);
        }
    }
}
