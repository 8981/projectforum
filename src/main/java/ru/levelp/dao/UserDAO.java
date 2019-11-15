package ru.levelp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.levelp.entity.User;

import javax.persistence.EntityManager;

@Repository
public class UserDAO {
    private EntityManager manager;

    @Autowired
    public UserDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create (User user) {
        manager.persist(user);
    }

    public User findByLogin(String login) {
        return manager.createQuery(
                "FROM User where login = :login", User.class)
                .setParameter("login", login)
                .getSingleResult();
    }

    public User findByLoginAndPasswordAndNicName(String login, String password, String nicName) {
        return manager.createQuery(
                "FROM User where login = :login AND password = :password AND nicName =:nicName", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .setParameter("nicName", nicName)
                .getSingleResult();
    }

    public User findById(int userId) {
        return manager.find(User.class, userId);
    }
}
