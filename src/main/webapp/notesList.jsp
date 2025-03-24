<%@ page import="java.util.List" %>
<%@ page import="uk.ac.ucl.model.NotesRecord" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <jsp:include page="/meta.jsp"/>
  <title>Notes generator</title>
</head>
<body>
<jsp:include page="/header.jsp"/>
<div class="main">
  <h2>Notes:</h2>
  <ul>
    <%
      List<NotesRecord> notes = (List<NotesRecord>) request.getAttribute("notes");
      if (notes != null && !notes.isEmpty()) {
        for (NotesRecord note : notes) {
          String href = "dummypage.html";
    %>
    <li><a href="<%=href%>"><%=note.noteName()%></a> - <%=note.text()%></li>
    <%
      }
    } else {
    %>
    <li>No notes available</li>
    <%
      }
    %>
  </ul>
</div>
<jsp:include page="/footer.jsp"/>
</body>
</html>