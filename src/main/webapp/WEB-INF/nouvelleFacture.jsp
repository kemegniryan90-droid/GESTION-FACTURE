<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nouvelle facture</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/menu.jsp" %>
    <h1>Nouvelle facture</h1>
    <div class="contenu">
        <form action="${pageContext.request.contextPath}/factures/nouvelle" method="post">

            <label for="client">Client :</label>
            <select name="idClient" id="client" required>
                <c:forEach var="client" items="${clients}">
                    <option value="${client.id}">${client.nom}</option>
                </c:forEach>
            </select>

            <label for="date">Date :</label>
            <input type="date" name="date" id="date" required>

            <table id="tableLignes">
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
            <button type="button" onclick="ajouterLigne()">+ Ajouter une ligne</button>
            <br><br>
            <button type="submit">Enregistrer la facture</button>
        </form>
    </div>

    <script>
        function ajouterLigne() {
            var table = document.getElementById("tableLignes");
            var ligne = table.rows[1].cloneNode(true);
            table.appendChild(ligne);
        }
    </script>
</body>
</html>
