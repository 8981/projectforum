package ru.levelp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelp.dao.TopicDAO;
import ru.levelp.dao.UserDAO;
import ru.levelp.entity.Topic;
import ru.levelp.entity.User;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private UserDAO dao;

    @Autowired
    private TopicDAO td;

    public List<Topic> getTopic(int userId) {
        User found = dao.findById(userId);
        return td.findByUser(found);
    }
}
