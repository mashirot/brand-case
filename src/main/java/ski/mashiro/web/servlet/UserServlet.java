package ski.mashiro.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import ski.mashiro.pojo.User;
import ski.mashiro.service.UserService;
import ski.mashiro.util.DeserializeJsonUtils;

import java.io.IOException;
import java.util.List;

/**
 * @author FeczIne
 */
@WebServlet(name = "UserServlet", value = "/user/*")
public class UserServlet extends BaseServlet{
    private static final String POST = "POST";
    UserService userService = new UserService();
    ObjectMapper objectMapper = new ObjectMapper();

    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            User user = objectMapper.readValue(DeserializeJsonUtils.deserializeJson(req), User.class);
            boolean rs = userService.getUser(user);
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write("{\"username\":\"" + user.getUsername() + "\",\"status\":\"" + rs + "\"}");
            if (rs && user.isRemember()) {
                Cookie username = new Cookie("username", user.getUsername());
                Cookie password = new Cookie("password", user.getPassword());
                username.setMaxAge(60 * 60 * 24);
                password.setMaxAge(60 * 60 * 24);
                username.setPath("/brand_case_war/login.html");
                password.setPath("/brand_case_war/login.html");
                resp.addCookie(username);
                resp.addCookie(password);
            }
            if (rs) {
                HttpSession session = req.getSession();
                session.setAttribute("username", user.getUsername());
                Cookie sessionId = new Cookie("JSESSIONID", session.getId());
                sessionId.setPath("/brand_case_war");
                resp.addCookie(sessionId);
            }
        }
    }

    public void saveUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            User user = objectMapper.readValue(DeserializeJsonUtils.deserializeJson(req), User.class);
            boolean rs = userService.saveUser(user);
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write("{\"username\":\"" + user.getUsername() + "\",\"status\":" + rs + "}");
        }
    }

    public void returnSession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write("{\"username\":\"" + req.getSession().getAttribute("username") + "\"}");
        }
    }

    public void deleteUserById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            String reqId = DeserializeJsonUtils.deserializeJson(req);
            String id = reqId.substring(reqId.lastIndexOf(":") + 1, reqId.lastIndexOf("}"));
            resp.setContentType("text/json;charset=utf-8");
            try {
                boolean rs = userService.deleteUserById(Integer.parseInt(id));
                resp.getWriter().write("{\"result\":" + rs + "}");
            } catch (Exception e) {
                resp.getWriter().write("{\"result\":" + false + "}");
            }
        }
    }
    public void listAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (POST.equals(req.getMethod())) {
            List<User> allUser = userService.listAllUser();
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(objectMapper.writeValueAsString(allUser));
        }
    }

}
