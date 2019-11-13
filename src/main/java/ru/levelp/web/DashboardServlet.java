package ru.levelp.web;

import ru.levelp.dao.TopicDAO;
import ru.levelp.entity.Topic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/dashboard")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory factory = StartupListener.getFactory(req.getServletContext());
        EntityManager manager = factory.createEntityManager();
        TopicDAO top = new TopicDAO(manager);
        try {
            int topicId = (int) req.getSession().getAttribute("topicId");
            Topic found = manager.find(Topic.class, topicId);
            List<Topic> transactions = top.findById(found);

            req.setAttribute("transactions", transactions);

            req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
        } catch (NoResultException notFound) {
            req.getRequestDispatcher("/").forward(req, resp);
        } finally {
            manager.close();
        }
    }
}
