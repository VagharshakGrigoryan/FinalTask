package servlet.user;

import manager.TaskManager;
import manager.UserManager;
import model.Task;
import model.User;
import model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/UserHome")
public class UserHomeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        TaskManager taskManager = new TaskManager();
        List<Task> allTaskByUserId = taskManager.getAllTaskByUserId(user.getId());
        req.setAttribute("tasks", allTaskByUserId);
        req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req, resp);
    }
}