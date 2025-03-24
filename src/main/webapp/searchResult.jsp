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
  <h1>Search Result</h1>
  <%
    List<?> results = (List<?>) request.getAttribute("result");
    if (results != null && !results.isEmpty()) {
      if (results.get(0) instanceof NotesRecord) {
  %>
  <h2>Notes:</h2>
  <ul>
    <%
      for (NotesRecord note : (List<NotesRecord>) results) {
    %>
    <li><a href="note.jsp?title=<%=note.noteName()%>"><%=note.noteName()%></a></li>
    <% } %>
  </ul>
  <%
  } else if (results.get(0) instanceof Category) {
  %>
  <h2>Categories:</h2>
  <ul>
    <%
      for (Category category : (List<Category>) results) {
    %>
    <li><a href="category.jsp?name=<%=category.getName()%>"><%=category.getName()%></a></li>
    <% } %>
  </ul>
  <%
    }
  } else {
  %>
  <p>Nothing found</p>
  <%
    }
  %>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>