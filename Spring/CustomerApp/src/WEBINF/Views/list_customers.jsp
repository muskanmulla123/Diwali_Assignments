<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Customers</title>
</head>
<body>
<h2>Customer List</h2>
<p><a href="${pageContext.request.contextPath}/customers/showFormForAdd">Add New Customer</a></p>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>Actions</th>
    </tr>
    <c:forEach var="c" items="${customers}">
        <tr>
            <td>${c.id}</td>
            <td>${c.firstName}</td>
            <td>${c.lastName}</td>
            <td>${c.email}</td>
            <td>
                <a href="${pageContext.request.contextPath}/customers/showFormForUpdate?customerId=${c.id}">Edit</a> |
                <a href="${pageContext.request.contextPath}/customers/delete?customerId=${c.id}"
                   onclick="return confirm('Delete this customer?')">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
