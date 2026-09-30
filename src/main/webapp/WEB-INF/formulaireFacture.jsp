<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Créer une facture</title>
</head>
<body>
    <h1>Créer une facture</h1>
    <form action="${pageContext.request.contextPath}/factures/creer" method="post">

        <label>Client :</label>
        <select name="idClient" required>
            <c:forEach var="client" items="${clients}">
                <option value="${client.id}">${client.nom}</option>
            </c:forEach>
        </select>
        <br><br>

        <table border="1">
            <tr>
                <th>Produit</th>
                <th>Quantité</th>
            </tr>
            <c:forEach var="i" begin="1" end="5">
                <tr>
                    <td>
                        <select name="idProduit">
                            <option value="">--</option>
                            <c:forEach var="produit" items="${produits}">
                                <option value="${produit.id}">${produit.designation} (${produit.prixUnitaire} FCFA)</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="number" name="quantite" min="1">
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br>

        <button type="submit">Créer la facture</button>
    </form>
    <a href="${pageContext.request.contextPath}/clients">Retour à la liste des clients</a>
</body>
</html>
