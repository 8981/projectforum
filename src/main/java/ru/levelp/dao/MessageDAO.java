package ru.levelp.dao;

import ru.levelp.entity.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

public class MessageDAO {
private final EntityManager manager;


    public MessageDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create(Message message) {
        manager.persist(message);
    }


    public void deleteMessage(long id) {
        Message message = manager.find(Message.class, id);
        if (message == null) throw new EntityNotFoundException("Message with id" + id + "is not found");
        manager.remove(message);
    }
}
