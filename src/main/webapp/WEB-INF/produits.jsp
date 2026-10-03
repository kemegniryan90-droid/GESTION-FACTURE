<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des produits</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/menu.jsp" %>
    <h1>Liste des produits</h1>
    <div class="contenu">
        <a class="bouton" href="${pageContext.request.contextPath}/produits/ajouter">Ajouter un produit</a>
        <table>
            <tr>
                <th>ID</th>
                <th>Désignation</th>
                <th>Prix unitaire</th>
                <th>Stock</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="produit" items="${produits}">
                <tr>
                    <td>${produit.id}</td>
                    <td>${produit.designation}</td>
                    <td>${produit.prixUnitaire} FCFA</td>
                    <td>${produit.stock}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/produits/supprimer?id=${produit.id}"
                           onclick="return confirm('Supprimer ce produit ?')">Supprimer</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
