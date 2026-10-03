<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un client</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <%@ include file="/WEB-INF/menu.jsp" %>
    <h1>Ajouter un client</h1>
    <div class="contenu">
        <form action="${pageContext.request.contextPath}/clients/ajouter" method="post">
            <label>Nom :</label>
            <input type="text" name="nom" required>

            <label>Téléphone :</label>
            <input type="text" name="telephone">

            <label>Email :</label>
            <input type="email" name="email">

            <br><br>
            <button type="submit">Enregistrer</button>
        </form>
        <br>
        <a href="${pageContext.request.contextPath}/clients">Retour à la liste</a>
    </div>
</body>
</html>
