package ru.levelp.dao;

import org.junit.*;
import ru.levelp.entity.Topic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

public class TopicDAOTest {
    private EntityManagerFactory factory;
    private EntityManager manager;
    private TopicDAO dao;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
        dao = new TopicDAO(manager);
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
        Topic topic = new Topic();
        manager.getTransaction().begin();
        try {
            topic.setTopicName("JavaDeveloper");
            dao.create(topic);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        assertNotNull(manager.find(Topic.class, topic.getId()));
    }

    @Test
    public void findById() {
        Topic topic = new Topic();
        manager.getTransaction().begin();
        try {
            topic.setTopicName("JavaDeveloper");
            dao.create(topic);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }
        Assert.assertEquals(topic.getId(),dao.findById(topic).get(0).getId());
    }
}