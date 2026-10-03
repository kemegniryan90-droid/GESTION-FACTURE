<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un produit</title>
</head>
<body>
    <h1>Ajouter un produit</h1>
    <form action="${pageContext.request.contextPath}/produits/ajouter" method="post">
        <label>Désignation :</label>
        <input type="text" name="designation" required><br>

        <label>Prix unitaire (FCFA) :</label>
        <input type="number" name="prixUnitaire" step="0.01" min="0" required><br>

        <label>Stock :</label>
        <input type="number" name="stock" min="0" required><br>

        <button type="submit">Enregistrer</button>
    </form>
    <a href="${pageContext.request.contextPath}/produits">Retour à la liste</a>
</body>
</html>
