package ru.levelp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue
    private long id;

    @Column(length = 250, nullable = false)
    private String topicName;

    @OneToMany
    private List<Topic> topics;

    @OneToMany
    private List<User> members;

    @OneToMany
    private List<Message> messages;

    public Topic() {}

    public Topic(String topicName, List<User> members, List<Message> messages, List<Topic> topics) {
        this.topicName = topicName;
        this.topics = topics;
        this.members = members;
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

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
