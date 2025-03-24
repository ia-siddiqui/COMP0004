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

@WebServlet("/notes")
public class NoteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        List<NotesRecord> notes = model.getNotes();
        request.setAttribute("notes", notes);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/index.jsp");
        dispatch.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            NotesRecord note = new NotesRecord(
                    request.getParameter("title"),
                    request.getParameter("content"),
                    request.getParameter("url"),
                    request.getParameter("imagePath"),
                    null,
                    request.getParameter("noteType")
            );
            model.addNote(note);
        } else if ("delete".equals(action)) {
            String title = request.getParameter("title");
            NotesRecord note = model.getNotes().stream().filter(n -> n.noteName().equals(title)).findFirst().orElse(null);
            if (note != null) {
                model.deleteNote(note);
            }
        } else if ("update".equals(action)) {
            NotesRecord note = new NotesRecord(
                    request.getParameter("title"),
                    request.getParameter("content"),
                    request.getParameter("url"),
                    request.getParameter("imagePath"),
                    null,
                    request.getParameter("noteType")
            );
            model.updateNote(note);
        } else if ("sortByTitle".equals(action)) {
            List<NotesRecord> sortedNotes = model.sortNotesByTitle();
            request.setAttribute("notes", sortedNotes);
        } else if ("sortByDate".equals(action)) {
            List<NotesRecord> sortedNotes = model.sortNotesByDate();
            request.setAttribute("notes", sortedNotes);
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/index.jsp");
        dispatch.forward(request, response);
    }
}