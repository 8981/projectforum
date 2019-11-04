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
}
