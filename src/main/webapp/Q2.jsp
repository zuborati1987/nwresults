<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting</title>
</head>
<body>
<h1>Results:</h1>

<c:if test="${empty results}">
        <span>No associated Data</span>
    </c:if>
    <c:if test="${not empty results}">
<table border="1">
    <tr>
        <th>Company</th>
        <th>Number of products</th>
    </tr>
    <c:forEach var="result" items="${results}">
        <tr>
            <td>${result.getCompanyName()}</td>
            <td>${result.getProductNum()}</td>
        </tr>
    </c:forEach>
</table>
</c:if>

<h2>Search by name</h2>
<form action="query2" method="post">    <select name="name" multiple>
        <c:forEach var="result" items="${results}">
            <option value="<c:out value="${result.getCompanyName()}"/>">${result.getCompanyName()}</option>
        </c:forEach>
    </select>
    <input type="submit">
</form>

<a href="index.html">Go back to the <em>index</em> page.</a>
<br>
</body>
</html>
