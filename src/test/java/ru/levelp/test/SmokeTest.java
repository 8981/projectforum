package ru.levelp.test;

import org.junit.*;
import ru.levelp.entity.Message;
import ru.levelp.entity.Topic;
import ru.levelp.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SmokeTest {
    private EntityManagerFactory factory;
    private EntityManager manager;

    @Before
    public void setup() {
        factory = Persistence.createEntityManagerFactory("TestPersistenceUnit");
        manager = factory.createEntityManager();
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
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setUserName("John Black");
        user.setLogin("login");
        user.setPassword("1234");

        manager.getTransaction().begin();
        try {
            manager.persist(user);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Assert.assertNotNull(manager.find(User.class, user.getId()));
    }

    @Test
    public void testCreateMessage() throws Exception{
        Message message = new Message();
        message.setMessage("Hello World");

        manager.getTransaction().begin();
        try {
            manager.persist(message);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Assert.assertNotNull(manager.find(Message.class, message.getId()));
    }

    @Test
    public void testCreateTopic() {
        Topic topic = new Topic();
        topic.setTopicName("First step in java");

        manager.getTransaction().begin();
        try {
            manager.persist(topic);
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            throw e;
        }

        Assert.assertNotNull(manager.find(Topic.class, topic.getId()));
    }
}
