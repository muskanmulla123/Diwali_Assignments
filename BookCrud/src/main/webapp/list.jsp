<%@ page import="java.util.*, model.Book" %>
<html>
<head>
    <title>Book List</title>
</head>
<body>
<h2>Book Collection</h2>
<p><a href="BookServlet?action=new">Add New Book</a></p>

<table border="1" cellpadding="5">
<tr>
    <th>ID</th><th>Title</th><th>Author</th><th>Publisher</th><th>Price</th><th>Actions</th>
</tr>
<%
    List<Book> list = (List<Book>) request.getAttribute("listBooks");
    if (list != null) {
        for (Book b : list) {
%>
<tr>
    <td><%= b.getId() %></td>
    <td><%= b.getTitle() %></td>
    <td><%= b.getAuthor() %></td>
    <td><%= b.getPublisher() %></td>
    <td><%= b.getPrice() %></td>
    <td>
        <a href="BookServlet?action=edit&id=<%=b.getId()%>">Edit</a> |
        <a href="BookServlet?action=delete&id=<%=b.getId()%>"
           onclick="return confirm('Are you sure you want to delete this book?')">Delete</a>
    </td>
</tr>
<%
        }
    }
%>
</table>
</body>
</html>
