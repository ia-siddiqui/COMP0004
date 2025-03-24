<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.NotesRecord" %>
<%@ page import="uk.ac.ucl.model.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <jsp:include page="/meta.jsp"/>
    <title>Note Taking App</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
    <h2>Categories:</h2>
    <ul>
        <%
            List<Category> categories = (List<Category>) request.getAttribute("categories");
            for (Category category : categories) {
        %>
        <li><a href="category.jsp?name=<%=category.getName()%>"><%=category.getName()%></a></li>
        <% } %>
    </ul>
    <h2>Notes:</h2>
    <form method="POST" action="notes">
        <input type="hidden" name="action" value="sortByTitle"/>
        <input type="submit" value="Sort by Title"/>
    </form>
    <form method="POST" action="notes">
        <input type="hidden" name="action" value="sortByDate"/>
        <input type="submit" value="Sort by Date"/>
    </form>
    <ul>
        <%
            List<NotesRecord> notes = (List<NotesRecord>) request.getAttribute("notes");
            for (NotesRecord note : notes) {
        %>
        <li><a href="note.jsp?title=<%=note.noteName()%>"><%=note.noteName()%></a></li>
        <% } %>
    </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>