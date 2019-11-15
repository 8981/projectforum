package ru.levelp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.levelp.dao.UserDAO;
import ru.levelp.entity.User;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private UserDAO users;

    @PostMapping(path = "/login")
    public String processLogin(
            HttpSession session,
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String nicName,
            ModelMap model) {
        try {
            User found = users.findByLoginAndPasswordAndNicName(login, password, nicName);
            session.setAttribute("userId", found.getId());

            return "redirect:/dashboardTopic";
        } catch (NoResultException notFound) {
            model.addAttribute("login", "login");
            return "page";
        }
    }
}