package com.gestionfacture.servlet;

import com.gestionfacture.dao.ClientDAO;
import com.gestionfacture.dao.EntrepriseDAO;
import com.gestionfacture.dao.FactureDAO;
import com.gestionfacture.dao.LigneFactureDAO;
import com.gestionfacture.dao.ProduitDAO;
import com.gestionfacture.entite.Client;
import com.gestionfacture.entite.Entreprise;
import com.gestionfacture.entite.Facture;
import com.gestionfacture.entite.LigneFacture;
import com.gestionfacture.entite.Produit;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/factures/pdf")
public class FacturePdfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FactureDAO factureDAO = new FactureDAO();
        ClientDAO clientDAO = new ClientDAO();
        EntrepriseDAO entrepriseDAO = new EntrepriseDAO();
        LigneFactureDAO ligneFactureDAO = new LigneFactureDAO();
        ProduitDAO produitDAO = new ProduitDAO();

        try {
            int idFacture = Integer.parseInt(request.getParameter("id"));

            Facture facture = factureDAO.recupererParId(idFacture);
            if (facture == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Facture introuvable");
                return;
            }

            Client client = clientDAO.recupererParId(facture.getIdClient());
            Entreprise entreprise = entrepriseDAO.recupererParId(facture.getIdEntreprise());
            List<LigneFacture> lignes = ligneFactureDAO.listerParFacture(idFacture);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + facture.getNumero() + ".pdf");

            PdfWriter writer = new PdfWriter(response.getOutputStream());
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph(entreprise.getNom()).setBold().setFontSize(16));
            document.add(new Paragraph(entreprise.getAdresse()));
            document.add(new Paragraph("Tél : " + entreprise.getTelephone()));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Facture N° " + facture.getNumero()).setBold().setFontSize(14));
            document.add(new Paragraph("Date : " + facture.getDate()));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Client : " + client.getNom()));
            document.add(new Paragraph("Téléphone : " + client.getTelephone()));
            document.add(new Paragraph(" "));

            Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1, 2, 2}));
            table.setWidth(UnitValue.createPercentValue(100));
            table.addHeaderCell(new Cell().add(new Paragraph("Produit").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Qté").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Prix unitaire").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Montant").setBold()));

            for (LigneFacture ligne : lignes) {
                Produit produit = produitDAO.recupererParId(ligne.getIdProduit());
                table.addCell(new Cell().add(new Paragraph(produit != null ? produit.getDesignation() : "Produit supprimé")));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(ligne.getQuantite()))));
                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", ligne.getPrixUnitaire()))));
                table.addCell(new Cell().add(new Paragraph(String.format("%.2f", ligne.getMontant()))));
            }

            document.add(table);
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Total : " + String.format("%.2f", facture.getTotal()) + " FCFA").setBold().setFontSize(13));

            document.close();

        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la génération du PDF", e);
        }
    }
}
