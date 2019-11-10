package ru.levelp.dao;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.levelp.entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class MessageDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private MessageDAO dao;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        dao = new MessageDAO(manager);
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
        Message message = new Message();
        manager.getTransaction().begin();
        try {
            message.setMessage("Hi");
            dao.create(message);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        assertNotNull(manager.find(Message.class, message.getId()));
    }

    @Test
    public void findById() {
        Message message = new Message();
        manager.getTransaction().begin();
        try {
            message.setMessage("Hi");
            dao.create(message);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        Assert.assertEquals(message.getId(),dao.findById(message).get(0).getId());
    }
}