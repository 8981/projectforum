package ru.levelp.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50, unique = true)
    private String login;

    @Column(nullable = false, length = 50, unique = true)
    private String password;

    @Column(nullable = false, length = 20, unique = true)
    private String nicName;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    public User() {
    }

    public User(String login, String password, String nicName, User user) {
        this.login = login;
        this.password = password;
        this.nicName = nicName;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNicName() {
        return nicName;
    }

    public void setNicName(String nicName) {
        this.nicName = nicName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}