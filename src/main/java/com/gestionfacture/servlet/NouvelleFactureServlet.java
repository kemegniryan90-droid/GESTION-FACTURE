package com.gestionfacture.servlet;

import com.gestionfacture.dao.ClientDAO;
import com.gestionfacture.dao.FactureDAO;
import com.gestionfacture.dao.LigneFactureDAO;
import com.gestionfacture.dao.ProduitDAO;
import com.gestionfacture.entite.Client;
import com.gestionfacture.entite.Facture;
import com.gestionfacture.entite.LigneFacture;
import com.gestionfacture.entite.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/factures/nouvelle")
public class NouvelleFactureServlet extends HttpServlet {

    private static final int ID_ENTREPRISE = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientDAO clientDAO = new ClientDAO();
        ProduitDAO produitDAO = new ProduitDAO();
        try {
            List<Client> clients = clientDAO.listerTous();
            List<Produit> produits = produitDAO.listerTous();
            request.setAttribute("clients", clients);
            request.setAttribute("produits", produits);
            request.getRequestDispatcher("/WEB-INF/nouvelleFacture.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors du chargement du formulaire de facture", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FactureDAO factureDAO = new FactureDAO();
        LigneFactureDAO ligneFactureDAO = new LigneFactureDAO();
        ProduitDAO produitDAO = new ProduitDAO();

        try {
            int idClient = Integer.parseInt(request.getParameter("idClient"));
            LocalDate date = LocalDate.parse(request.getParameter("date"));
            String[] idsProduits = request.getParameterValues("produit[]");
            String[] quantites = request.getParameterValues("quantite[]");

            double total = 0;
            for (int i = 0; i < idsProduits.length; i++) {
                int idProduit = Integer.parseInt(idsProduits[i]);
                int quantite = Integer.parseInt(quantites[i]);
                Produit produit = produitDAO.listerTous().stream()
                        .filter(p -> p.getId() == idProduit)
                        .findFirst()
                        .orElseThrow();
                total += produit.getPrixUnitaire() * quantite;
            }

            String numero = "FAC-" + System.currentTimeMillis();

            Facture facture = new Facture();
            facture.setNumero(numero);
            facture.setDate(date);
            facture.setTotal(total);
            facture.setIdClient(idClient);
            facture.setIdEntreprise(ID_ENTREPRISE);
            int idFacture = factureDAO.ajouter(facture);

            for (int i = 0; i < idsProduits.length; i++) {
                int idProduit = Integer.parseInt(idsProduits[i]);
                int quantite = Integer.parseInt(quantites[i]);
                Produit produit = produitDAO.listerTous().stream()
                        .filter(p -> p.getId() == idProduit)
                        .findFirst()
                        .orElseThrow();

                LigneFacture ligne = new LigneFacture();
                ligne.setIdFacture(idFacture);
                ligne.setIdProduit(idProduit);
                ligne.setQuantite(quantite);
                ligne.setPrixUnitaire(produit.getPrixUnitaire());
                ligne.setMontant(produit.getPrixUnitaire() * quantite);
                ligneFactureDAO.ajouter(ligne);
            }

            response.sendRedirect(request.getContextPath() + "/factures/nouvelle?succes=1");

        } catch (SQLException e) {
            throw new ServletException("Erreur lors de l'enregistrement de la facture", e);
        }
    }
}
