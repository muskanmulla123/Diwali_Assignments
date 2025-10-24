<%@ page import="model.Book" %>
<%
    Book book = (Book) request.getAttribute("book");
%>
<html>
<head><title>Edit Book</title></head>
<body>
<h2>Edit Book</h2>
<form action="BookServlet?action=update" method="post">
    <input type="hidden" name="id" value="<%=book.getId()%>">
    Title: <input type="text" name="title" value="<%=book.getTitle()%>" required><br><br>
    Author: <input type="text" name="author" value="<%=book.getAuthor()%>" required><br><br>
    Publisher: <input type="text" name="publisher" value="<%=book.getPublisher()%>"><br><br>
    Price: <input type="number" name="price" step="0.01" value="<%=book.getPrice()%>" required><br><br>
    <input type="submit" value="Update">
</form>
<p><a href="BookServlet?action=list">Back to List</a></p>
</body>
</html>
