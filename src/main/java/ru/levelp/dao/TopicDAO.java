package ru.levelp.dao;

import ru.levelp.entity.Topic;

import javax.persistence.EntityManager;

public class TopicDAO {
    private final EntityManager manager;

    public TopicDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create(Topic topic) {
        manager.persist(topic);
    }

    public Topic findByTopicName(String topicName) {
        return manager.createQuery(
                "FROM Topic where topicName = :topicName", Topic.class)
                .setParameter("topicName", topicName)
                .getSingleResult();
    }
}
