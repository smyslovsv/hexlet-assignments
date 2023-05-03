package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;

import static exercise.Data.getUsers;
import static exercise.Data.getNextId;

public class UsersServlet extends HttpServlet {

    private List<Map<String, String>> users = getUsers();

    private String getId(HttpServletRequest request) {
        return request.getParameter("id");
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, "");
    }

    private Map<String, String> getUserById(String id) {
        return users
                .stream()
                .filter(u -> u.get("id").equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list" -> showUsers(request, response);
            case "new" -> newUser(request, response);
            case "edit" -> editUser(request, response);
            case "show" -> showUser(request, response);
            case "delete" -> deleteUser(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "new" -> createUser(request, response);
            case "edit" -> updateUser(request, response);
            case "delete" -> destroyUser(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException, ServletException {

        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(request, response);
    }


    private void showUser(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(request, response);
    }

    private void newUser(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        Map<String, String> user = new HashMap<>();

        if (response.getStatus() == 422) {
            user.put("lastName", request.getParameter("lastName"));
            user.put("firstName", request.getParameter("firstName"));
            user.put("email", request.getParameter("email"));
            request.setAttribute("error", "Проверьте данные формы");
            response.setStatus(422);
        } else {
            request.setAttribute("error", "");
            response.setStatus(HttpServletResponse.SC_OK);
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void createUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        Map<String, String> user = new HashMap<>();

        user.put("lastName", request.getParameter("lastName"));
        user.put("firstName", request.getParameter("firstName"));
        user.put("email", request.getParameter("email"));

        if (user.get("lastName").isEmpty() || user.get("firstName").isEmpty()) {
            response.setStatus(422);
            request.setAttribute("user", user);
            request.setAttribute("error", "Проверьте данные формы");
            //response.sendRedirect("/users/new");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        user.put("id", getNextId());
        users.add(user);
        response.setStatus(HttpServletResponse.SC_OK);
        response.sendRedirect("/users");
        // END
    }

    private void editUser(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        if (response.getStatus() == 422) {
            Map<String, String> user2 = new HashMap<>();
            user2.put("lastName", request.getParameter("lastName"));
            user2.put("firstName", request.getParameter("firstName"));
            user2.put("email", request.getParameter("email"));
            user2.put("id", id);
            request.setAttribute("error", "Проверьте данные формы");
            request.setAttribute("user", user2);
            response.setStatus(422);
        } else {
            request.setAttribute("user", user);
            request.setAttribute("error", "");
            response.setStatus(200);
        }

        // BEGIN
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void updateUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // BEGIN
        Map<String, String> updatedUser = new HashMap<>();
        updatedUser.put("lastName", request.getParameter("lastName"));
        updatedUser.put("firstName", request.getParameter("firstName"));
        updatedUser.put("email", request.getParameter("email"));
        updatedUser.put("id", id);

        if (updatedUser.get("lastName").isEmpty() || updatedUser.get("firstName").isEmpty()) {
            response.setStatus(422);
            request.setAttribute("user", updatedUser);
            request.setAttribute("id", id);
            request.setAttribute("error", "Проверьте данные формы");
            response.sendRedirect("/users/edit?id=" + id);
//            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/users/edit.jsp?id=" + id);
//            requestDispatcher.forward(request, response);

        } else {
            users.set(users.indexOf(user), updatedUser);
            response.sendRedirect("/users/show?id=" + id);// END
        }
    }

    private void deleteUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/delete.jsp");
        requestDispatcher.forward(request, response);
    }

    private void destroyUser(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        users.remove(user);
        response.sendRedirect("/users");
    }
}
