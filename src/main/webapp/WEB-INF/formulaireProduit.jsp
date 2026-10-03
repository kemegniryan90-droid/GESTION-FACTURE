<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un produit</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/menu.jsp" %>
    <h1>Ajouter un produit</h1>
    <div class="contenu">
        <form action="${pageContext.request.contextPath}/produits/ajouter" method="post">
            <label>Désignation :</label>
            <input type="text" name="designation" required>

            <label>Prix unitaire (FCFA) :</label>
            <input type="number" name="prixUnitaire" step="0.01" min="0" required>

            <label>Stock :</label>
            <input type="number" name="stock" min="0" required>

            <br><br>
            <button type="submit">Enregistrer</button>
        </form>
        <br>
        <a href="${pageContext.request.contextPath}/produits">Retour à la liste</a>
    </div>
</body>
</html>
