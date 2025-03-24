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

import java.io.IOException;
import java.util.List;

@WebServlet("/notesList.html")
public class ViewNoteListServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Model model = ModelFactory.getModel();
    List<NotesRecord> notes = model.getNotes();
    request.setAttribute("notes", notes);

    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/notesList.jsp");
    dispatch.forward(request, response);
  }
}