package ru.levelp.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 50, nullable = false, unique = true)
    private String userName;

    @Column(length = 50, nullable = false, unique = true)
    private String login;

    @Column(length = 50, nullable = false, unique = true)
    private String password;

    @ManyToOne
    private Topic topic;

    public User() {}

    public User(String userName, String login, String password, Topic topic) {
        this.userName = userName;
        this.login = login;
        this.password = password;
        this.topic = topic;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}