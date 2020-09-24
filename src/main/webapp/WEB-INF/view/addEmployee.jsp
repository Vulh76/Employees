<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Добавить сотрудника</title>
</head>
<body>
<form action="/add" method="POST">
    <label for="firstName">Имя</label>
    <input type="text" name="firstName" id="firstName">
    <label for="lastName">Фамилия</label>
    <input type="text" name="lastName" id="lastName">
    <label for="age">Возраст</label>
    <input type="text" name="age" id="age">
    <label for="department">Отдел</label>
    <input type="text" name="department" id="department">
    <input type="submit" value="Добавить">
</form>
</body>
</html>
