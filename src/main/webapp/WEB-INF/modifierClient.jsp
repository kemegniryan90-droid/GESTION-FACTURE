<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Modifier un client</title>
</head>
<body>
    <h1>Modifier le client</h1>
    <form action="modifier" method="post">
        <input type="hidden" name="id" value="${client.id}">

        <label>Nom :</label>
        <input type="text" name="nom" value="${client.nom}" required><br>

        <label>Téléphone :</label>
        <input type="text" name="telephone" value="${client.telephone}"><br>

        <label>Email :</label>
        <input type="email" name="email" value="${client.email}"><br>

        <button type="submit">Enregistrer</button>
    </form>
    <a href="../clients">Retour à la liste</a>
</body>
</html>
