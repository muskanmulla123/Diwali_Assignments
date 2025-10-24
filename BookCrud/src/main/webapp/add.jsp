<html>
<head><title>Add Book</title></head>
<body>
<h2>Add New Book</h2>
<form action="BookServlet?action=insert" method="post">
    Title: <input type="text" name="title" required><br><br>
    Author: <input type="text" name="author" required><br><br>
    Publisher: <input type="text" name="publisher"><br><br>
    Price: <input type="number" name="price" step="0.01" required><br><br>
    <input type="submit" value="Save">
</form>
<p><a href="BookServlet?action=list">Back to List</a></p>
</body>
</html>
