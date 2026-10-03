<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des clients</title>
</head>
<body>
    <%@ include file="/WEB-INF/menu.jsp" %>
    <h1>Liste des clients</h1>
    <a href="${pageContext.request.contextPath}/clients/ajouter">Ajouter un client</a>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Nom</th>
            <th>Téléphone</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="client" items="${clients}">
            <tr>
                <td>${client.id}</td>
                <td>${client.nom}</td>
                <td>${client.telephone}</td>
                <td>${client.email}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/clients/modifier?id=${client.id}">Modifier</a>
                    <a href="${pageContext.request.contextPath}/clients/supprimer?id=${client.id}">Supprimer</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
