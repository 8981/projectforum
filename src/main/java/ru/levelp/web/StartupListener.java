package ru.levelp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ru.levelp.dao.TopicDAO;
import ru.levelp.dao.UserDAO;
import ru.levelp.entity.Topic;
import ru.levelp.entity.User;
import javax.persistence.*;

@Component
public class StartupListener {

    @Autowired
    private UserDAO dao;

    @Autowired
    private TopicDAO td;

    @Autowired
    private EntityManager manager;

    @EventListener
    public void handleContextRefreshEvent(ContextRefreshedEvent ctxStartEvt) {
        User user;
        manager.getTransaction().begin();
        try {
            user = dao.findByLogin("login");
        } catch (NoResultException notFound) {
            user = new User("login", "1234","Black");

            dao.create(user);

            for (int i = 0; i < 10; ++i) {
                td.create(new Topic());
            }

            manager.getTransaction().commit();
        }
    }
}
