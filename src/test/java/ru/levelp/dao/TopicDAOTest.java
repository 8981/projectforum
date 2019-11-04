package ru.levelp.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
            topic.setTopicName("Java developer");
            manager.persist(topic);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        assertNotNull(manager.find(Topic.class, topic.getId()));
    }

    @Test
    public void findByTopicName() {
        Topic topic = new Topic();
        manager.getTransaction().begin();
        try {
            topic.setTopicName("Java developer");
            manager.persist(topic);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Topic found = dao.findByTopicName("Java developer");
        assertNotNull(found);
        assertEquals(topic.getId(), found.getId());
    }
}