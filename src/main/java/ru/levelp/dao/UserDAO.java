package ru.levelp.dao;

import ru.levelp.entity.User;

import javax.persistence.EntityManager;

public class UserDAO {
    private EntityManager manager;

    public UserDAO(EntityManager manager) {
        this.manager = manager;
    }

    public void create (User user) {
        manager.persist(user);
    }

    public User findByLoginAndPasswordAndNicName(String login, String password, String nicName) {
        return manager.createQuery(
                "FROM User where login = :login AND password = :password AND nicName =:nicName", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .setParameter("nicName", nicName)
                .getSingleResult();
    }
}
