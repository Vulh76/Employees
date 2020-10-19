<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>

<h2>Сотрудники</h2>
<table>
    <tr>
        <th><a href="/employees?page=${page}&sort=id">ID</a></th>
        <th><a href="/employees?page=${page}&sort=firstName">Имя</a></th>
        <th><a href="/employees?page=${page}&sort=lastName">Фамилия</a></th>
        <th><a href="/employees?page=${page}&sort=age">Возраст</a></th>
        <th><a href="/employees?page=${page}&sort=department">Отдел</a></th>
        <th>Действие</th>
    </tr>
    <c:forEach var="employee" items="${employeesList}">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.firstName}</td>
            <td>${employee.lastName}</td>
            <td>${employee.age}</td>
            <td>${employee.department.name}</td>
            <td>
                <a href="/edit?id=${employee.id}">Редактировать</a>
                <a href="/delete?id=${employee.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
<h2></h2>
<c:url value="/add" var="add"/><a href="${add}">Добавить сотрудника</a>
<h2></h2>
<c:forEach begin="1" end="${pagesCount}" step="1" varStatus="i">
    <c:url value="?page=" var="url">
        <c:param name="page" value="${i.index}"/>
    </c:url>
    <a href="${url}">${i.index}</a>
</c:forEach>
</body>
</html>