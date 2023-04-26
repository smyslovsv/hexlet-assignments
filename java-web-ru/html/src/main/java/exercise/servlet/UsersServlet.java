package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<Users> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper mapper = new ObjectMapper();
        Path readFilePath = Paths.get("src/main/resources/users.json");

        String inputString = Files.readString(readFilePath);
        List<Users> users = mapper.readValue(inputString, new TypeReference<List<Users>>(){});

        return users;
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Users> usersList = getUsers();
        // Создаем html разметку при помощи String builder и подключаем стили
        StringBuilder body = new StringBuilder();
        body.append("""
            <!DOCTYPE html>
            <html lang="ru">
                <head>
                    <meta charset="UTF-8">
                    <title>Example application | Users</title>
                    <link rel="stylesheet" href="mysite.css">
                </head>
                <body>
                    <table>
            """);

        for (Users user : usersList) {
            body.append("<tr><td>")
                    .append(user.getId())
                    .append("</td><td><a href=\"/users/")
                    .append(user.getId())
                    .append("\">")
                    .append(user.getFirstName())
                    .append(" ")
                    .append(user.getLastName())
                    .append("</a></td></tr>");
        }

        body.append("""
                    </table>
                </body>
            </html>
            """);

        // Устанавливаем тип содержимого ответа и кодировку
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(body.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Users> usersList = getUsers();
        if (usersList.stream().anyMatch(user -> user.getId().equals(id))) {
            // Создаем html разметку при помощи String builder и подключаем стили
            StringBuilder body = new StringBuilder();
            body.append("""
                    <!DOCTYPE html>
                    <html lang="ru">
                        <head>
                            <meta charset="UTF-8">
                            <title>Example application | Users</title>
                            <link rel="stylesheet" href="mysite.css">
                        </head>
                        <body>
                            <table>
                    """);

            for (Users user : usersList) {
                if (user.getId().equals(id)) {
                    body.append("<tr><td>")
                            .append(user.getId())
                            .append("</td><td>")
                            .append(user.getFirstName())
                            .append("</td><td>")
                            .append(user.getLastName())
                            .append("</td><td>")
                            .append(user.getEmail())
                            .append("</td></tr>");
                }
            }

            body.append("""
                            </table>
                        </body>
                    </html>
                    """);

            // Устанавливаем тип содержимого ответа и кодировку
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println(body.toString());
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        // END
    }
}
