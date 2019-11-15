package ru.levelp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.levelp.entity.Topic;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class DashboardController {
    @Autowired
    private DashboardService dashboard;

    @GetMapping(path = "/dashboardTopics")
    public String dashboard(HttpSession session, ModelMap model) {
        try {
            int userId = (int) session.getAttribute("userId");
            List<Topic> topics = dashboard.getTopic(userId);

            model.addAttribute("topics", topics);

            return "dashboardTopics";
        } catch (Exception e) {
            return "page";
        }
    }
}