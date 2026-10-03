<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nouvelle facture</title>
</head>
<body>
    <%@ include file="/WEB-INF/menu.jsp" %>
    <h1>Nouvelle facture</h1>
    <form action="${pageContext.request.contextPath}/factures/nouvelle" method="post">

        <label for="client">Client :</label>
        <select name="idClient" id="client" required>
            <c:forEach var="client" items="${clients}">
                <option value="${client.id}">${client.nom}</option>
            </c:forEach>
        </select>
        <br><br>

        <label for="date">Date :</label>
        <input type="date" name="date" id="date" required>
        <br><br>

        <table border="1" id="tableLignes">
            <tr>
                <th>Produit</th>
                <th>Quantité</th>
            </tr>
            <tr>
                <td>
                    <select name="produit[]" required>
                        <c:forEach var="produit" items="${produits}">
                            <option value="${produit.id}">${produit.designation} (${produit.prixUnitaire} FCFA)</option>
                        </c:forEach>
                    </select>
                </td>
                <td><input type="number" name="quantite[]" min="1" value="1" required></td>
            </tr>
        </table>
        <br>
        <button type="button" onclick="ajouterLigne()">+ Ajouter une ligne</button>
        <br><br>

        <button type="submit">Enregistrer la facture</button>
    </form>

    <script>
        function ajouterLigne() {
            var table = document.getElementById("tableLignes");
            var ligne = table.rows[1].cloneNode(true);
            table.appendChild(ligne);
        }
    </script>
</body>
</html>
