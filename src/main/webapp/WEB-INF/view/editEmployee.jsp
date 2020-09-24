<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Редактировать данные сотрудника</title>
</head>
<body>
<form action="/edit" method="POST">
    <input type="hidden" name="id" value="${employee.id}">
    <label for="firstName">Имя</label>
    <input type="text" name="firstName" id="firstName" value="${employee.firstName}">
    <label for="lastName">Фамилия</label>
    <input type="text" name="lastName" id="lastName" value="${employee.lastName}">
    <label for="age">Возраст</label>
    <input type="text" name="age" id="age" value="${employee.age}">
    <label for="department">Отдел</label>
    <input type="text" name="department" id="department" value="${employee.department}">
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
