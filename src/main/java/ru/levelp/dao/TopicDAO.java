package ru.levelp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.levelp.entity.Topic;
import ru.levelp.entity.User;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class TopicDAO {
    private EntityManager manager;

    @Autowired
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

    public List<Topic> findByUser(User user) {
        return manager.createQuery(
                "from Topic where id = :p", Topic.class).setParameter("p", user.getId())
                .getResultList();
    }
}
