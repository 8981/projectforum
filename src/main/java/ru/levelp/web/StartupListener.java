package ru.levelp.web;

import ru.levelp.dao.TopicDAO;
import ru.levelp.dao.UserDAO;
import ru.levelp.entity.Topic;
import ru.levelp.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Random;

@WebListener
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");

        User testUser;
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        UserDAO dao = new UserDAO(manager);
        TopicDAO top = new TopicDAO(manager);
        try {
            testUser = dao.findByLogin("test");
        } catch (NoResultException notFound) {
            testUser = new User("login", "password","Black");

            dao.create(testUser);

            for (int i = 0; i < 10; ++i) {
                top.create(new Topic());
            }

            manager.getTransaction().commit();
        } finally {
            manager.close();
        }

        event.getServletContext().setAttribute("factory", factory);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        EntityManagerFactory factory = getFactory(event.getServletContext());

        if (factory != null) {
            factory.close();
        }
    }

    public static EntityManagerFactory getFactory(ServletContext context) {
        return (EntityManagerFactory) context.getAttribute("factory");
    }
}
