<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des factures</title>
</head>
<body>
    <%@ include file="/WEB-INF/menu.jsp" %>
    <h1>Liste des factures</h1>
    <a href="${pageContext.request.contextPath}/factures/nouvelle">Nouvelle facture</a>
    <table border="1">
        <tr>
            <th>Numéro</th>
            <th>Date</th>
            <th>Client</th>
            <th>Total</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="facture" items="${factures}">
            <tr>
                <td>${facture.numero}</td>
                <td>${facture.date}</td>
                <td>${nomsClients[facture.idClient]}</td>
                <td>${facture.total} FCFA</td>
                <td>
                    <a href="${pageContext.request.contextPath}/factures/pdf?id=${facture.id}">Télécharger PDF</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
