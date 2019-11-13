package ru.levelp.web;

import ru.levelp.dao.UserDAO;
import ru.levelp.entity.User;

import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String nicName = req.getParameter("nicName");

        EntityManagerFactory factory = StartupListener.getFactory(req.getServletContext());
        EntityManager manager = factory.createEntityManager();
        UserDAO dao = new UserDAO(manager);
        try {
            User found = dao.findByLoginAndPasswordAndNicName(login, password,nicName);
            req.getSession().setAttribute("userId", found.getId());
            resp.sendRedirect("/dashboard");
        } catch (NoResultException notFound) {
            req.getRequestDispatcher("/index.jsp?login=" + login).forward(req, resp);
        } finally {
            manager.close();
        }
    }
}
