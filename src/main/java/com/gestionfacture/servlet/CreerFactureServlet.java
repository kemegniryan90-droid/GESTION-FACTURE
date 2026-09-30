package com.gestionfacture.servlet;

import com.gestionfacture.dao.ClientDAO;
import com.gestionfacture.dao.FactureDAO;
import com.gestionfacture.dao.LigneFactureDAO;
import com.gestionfacture.dao.ProduitDAO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/factures/creer")
public class CreerFactureServlet extends HttpServlet {

    private static final int ID_ENTREPRISE = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("clients", new ClientDAO().listerTous());
            request.setAttribute("produits", new ProduitDAO().listerTous());
            request.getRequestDispatcher("/WEB-INF/formulaireFacture.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Erreur lors du chargement du formulaire", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idClient = Integer.parseInt(request.getParameter("idClient"));
        String[] idsProduit = request.getParameterValues("idProduit");
        String[] quantites = request.getParameterValues("quantite");

        try {
            // Construire un dictionnaire id -> Produit pour retrouver les prix rapidement
            Map<Integer, Produit> produitsParId = new HashMap<>();
            for (Produit p : new ProduitDAO().listerTous()) {
                produitsParId.put(p.getId(), p);
            }

            double total = 0;
            List<LigneFacture> lignes = new java.util.ArrayList<>();

            for (int i = 0; i < idsProduit.length; i++) {
                String idProduitStr = idsProduit[i];
                String quantiteStr = quantites[i];

                // On ignore les lignes laissées vides
                if (idProduitStr == null || idProduitStr.isEmpty()
                        || quantiteStr == null || quantiteStr.isEmpty()) {
                    continue;
                }

                int idProduit = Integer.parseInt(idProduitStr);
                int quantite = Integer.parseInt(quantiteStr);
                Produit produit = produitsParId.get(idProduit);

                double montant = produit.getPrixUnitaire() * quantite;
                total += montant;

                LigneFacture ligne = new LigneFacture();
                ligne.setIdProduit(idProduit);
                ligne.setQuantite(quantite);
                ligne.setPrixUnitaire(produit.getPrixUnitaire());
                ligne.setMontant(montant);
                lignes.add(ligne);
            }

            if (lignes.isEmpty()) {
                throw new ServletException("Aucune ligne de produit remplie");
            }

            // Enregistrer la facture d'abord, pour obtenir son id généré
            Facture facture = new Facture();
            facture.setNumero("FAC-" + System.currentTimeMillis());
            facture.setDate(LocalDate.now());
            facture.setTotal(total);
            facture.setIdClient(idClient);
            facture.setIdEntreprise(ID_ENTREPRISE);

            int idFacture = new FactureDAO().ajouter(facture);

            // Enregistrer chaque ligne, rattachée à cette facture
            LigneFactureDAO ligneFactureDAO = new LigneFactureDAO();
            for (LigneFacture ligne : lignes) {
                ligne.setIdFacture(idFacture);
                ligneFactureDAO.ajouter(ligne);
            }

            response.sendRedirect(request.getContextPath() + "/factures");
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la création de la facture", e);
        }
    }
}
