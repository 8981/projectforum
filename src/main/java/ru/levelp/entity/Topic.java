package ru.levelp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50)
    private String topicName;

    @OneToMany
    private List<Message> messages;

    @ManyToOne
    private User user;

    public Topic() {
    }

    public Topic(String topicName,List<Message> messages, User user) {
        this.topicName = topicName;
        this.messages = messages;
        this.user = user;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
