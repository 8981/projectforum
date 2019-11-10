package ru.levelp.dao;

import ru.levelp.entity.Message;

import javax.persistence.EntityManager;
import java.util.List;

public class MessageDAO {
    private EntityManager manager;

    public MessageDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create(Message message) {
        manager.persist(message);
    }

    public Message delete(Message message) {
        manager.remove(message);
        return message;
    }

    public List<Message> findById(Message message) {
        return manager.createQuery(
                "FROM Message where id = :id", Message.class)
                .setParameter("id", message.getId())
                .getResultList();
    }
}
