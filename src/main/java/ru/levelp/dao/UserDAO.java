package ru.levelp.dao;

import ru.levelp.entity.User;

import javax.persistence.EntityManager;

public class UserDAO {
    private final EntityManager manager;

    public UserDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create(User user) {
        manager.persist(user);
    }

    public User findByUserNameLoginAndPassword(String userName, String login, String password) {
       return manager.createQuery(
               "FROM User where userName = :userName AND login = :login AND password = :password", User.class)
               .setParameter("userName", userName)
               .setParameter("login", login)
               .setParameter("password", password)
               .getSingleResult();
    }
}
