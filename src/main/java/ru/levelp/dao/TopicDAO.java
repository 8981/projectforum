package ru.levelp.dao;

import ru.levelp.entity.Topic;
import ru.levelp.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

public class TopicDAO {
    private EntityManager manager;

    public TopicDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create(Topic topic) {
        manager.persist(topic);
    }

    public Topic delete(Topic topic) {
        manager.remove(topic);
        return topic;
    }

    public List<Topic> findById(Topic topic) {
        return manager.createQuery(
                "FROM Topic where id = :id", Topic.class)
                .setParameter("id", topic.getId())
                .getResultList();
    }
}
