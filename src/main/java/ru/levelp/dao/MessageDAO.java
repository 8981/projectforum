package ru.levelp.dao;

import ru.levelp.entity.Message;

import javax.persistence.EntityManager;

public class MessageDAO {
private final EntityManager manager;


    public MessageDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create(Message message) {
        manager.persist(message);
    }

    public Message findByMessage(String message) {
        return manager.createQuery("FROM Message where message = :message", Message.class)
                .setParameter("message", message)
                .getSingleResult();
    }
}
