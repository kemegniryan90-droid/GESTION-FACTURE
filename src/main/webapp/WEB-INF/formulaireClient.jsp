<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Ajouter un client</title>
</head>
<body>
    <h1>Ajouter un client</h1>
    <form action="clients/ajouter" method="post">
        <label>Nom :</label>
        <input type="text" name="nom" required><br>

        <label>Téléphone :</label>
        <input type="text" name="telephone"><br>

        <label>Email :</label>
        <input type="email" name="email"><br>

        <button type="submit">Enregistrer</button>
    </form>
    <a href="clients">Retour à la liste</a>
</body>
</html>
