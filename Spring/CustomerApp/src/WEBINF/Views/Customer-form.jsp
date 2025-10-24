<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Customer Form</title>
</head>
<body>
<h2>Customer Form</h2>

<form:form action="${pageContext.request.contextPath}/customers/save" modelAttribute="customer" method="post">
    <form:hidden path="id" />
    <table>
        <tr>
            <td>First Name:</td>
            <td><form:input path="firstName" /></td>
        </tr>
        <tr>
            <td>Last Name:</td>
            <td><form:input path="lastName" /></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><form:input path="email" /></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form:form>

<p><a href="${pageContext.request.contextPath}/customers/list">Back to List</a></p>
</body>
</html>
