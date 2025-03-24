package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.NotesRecord;
import uk.ac.ucl.model.Category;

import java.io.IOException;
import java.util.List;
// The servlet invoked to perform a search.
// The url http://localhost:8090/runsearch.html is mapped to calling doPost on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/runsearch.html")
public class SearchServlet extends HttpServlet {
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Model model = ModelFactory.getModel();
    String searchString = request.getParameter("searchstring");
    String searchType = request.getParameter("searchtype");

    if ("notes".equals(searchType)) {
      List<NotesRecord> searchResult = model.searchForNotes(searchString);
      request.setAttribute("result", searchResult);
    } else if ("categories".equals(searchType)) {
      List<Category> searchResult = model.searchForCategories(searchString);
      request.setAttribute("result", searchResult);
    }

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/searchResult.jsp");
    dispatch.forward(request, response);
  }
}