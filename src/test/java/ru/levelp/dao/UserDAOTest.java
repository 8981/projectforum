package ru.levelp.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.levelp.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class UserDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private UserDAO dao;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        dao = new UserDAO(manager);
    }

    @After
    public void cleanup() {
        if (manager != null) {
            manager.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    @Test
    public void create() {
        User user = new User();
        manager.getTransaction().begin();
        try {
            user.setLogin("login");
            user.setPassword("1234");
            user.setNicName("Black");
            dao.create(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        assertNotNull(manager.find(User.class, user.getId()));
    }

    @Test
    public void findByLogin() {
        User user = new User();
        manager.getTransaction().begin();
        try {
            user.setLogin("login");
            user.setPassword("1234");
            user.setNicName("Black");
            manager.persist(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        User found = dao.findByLogin("login");
        assertNotNull(found);
        assertEquals(user.getId(), found.getId());
    }

    @Test
    public void findByLoginAndPasswordAndNicName() {
        User user = new User();
        manager.getTransaction().begin();
        try {
            user.setLogin("login");
            user.setPassword("1234");
            user.setNicName("Black");
            dao.create(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        User found = dao.findByLoginAndPasswordAndNicName("login", "1234", "Black");
        assertNotNull(found);
        assertEquals(user.getId(), found.getId());
    }
}
