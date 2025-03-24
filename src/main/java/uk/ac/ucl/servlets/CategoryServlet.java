package uk.ac.ucl.servlets;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uk.ac.ucl.model.Category;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import java.io.IOException;
import java.util.List;

@WebServlet("/categories")
public class CategoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        List<Category> categories = model.getCategories();
        request.setAttribute("categories", categories);

        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/index.jsp");
        dispatch.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Model model = ModelFactory.getModel();
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            Category category = new Category(request.getParameter("name"));
            model.addCategory(category);
        } else if ("delete".equals(action)) {
            String name = request.getParameter("name");
            Category category = model.getCategories().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
            if (category != null) {
                model.deleteCategory(category);
            }
        } else if ("update".equals(action)) {
            String name = request.getParameter("name");
            Category category = model.getCategories().stream().filter(c -> c.getName().equals(name)).findFirst().orElse(null);
            if (category != null) {
                category.setName(request.getParameter("newName"));
                model.updateCategory();
            }
        }

        response.sendRedirect("categories");
    }
}