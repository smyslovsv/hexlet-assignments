package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    private static Database database;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
        database = DB.getDefault();
    }
    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("user");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users")
            .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
            .get(baseUrl + "/users/new")
            .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }
    @Test
    void testCreateUser() {
        User user = new User("Fedor", "Sumkin", "hobbit@shire.gov", "24022022");

        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("firstName", user.getFirstName())
                .field("lastName", user.getLastName())
                .field("email", user.getEmail())
                .field("password", user.getPassword())
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(302);

        User actualUser = new QUser()
                .lastName.equalTo(user.getLastName())
                .findOne();

        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(actualUser.getLastName()).isEqualTo(user.getLastName());
        assertThat(actualUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(actualUser.getPassword()).isEqualTo(user.getPassword());
    }
    void testCreateUserWhiteWrongData() {
        User user = new User("", "", "hobbit-shire.gov", "240");

        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                .field("firstName", user.getFirstName())
                .field("lastName", user.getLastName())
                .field("email", user.getEmail())
                .field("password", user.getPassword())
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);

        String content = responsePost.getBody();

        assertThat(content).contains("Имя не должно быть пустым");
        assertThat(content).contains("Фамилия не должна быть пустой");
        assertThat(content).contains("Должно быть валидным email");
        assertThat(content).contains("Пароль должен содержать не менее 4 символов");

        User userCheck = new QUser()
                .firstName.equalTo(user.getFirstName())
                .findOne();

        assertThat(userCheck).isNull();
    }

    // BEGIN
    @AfterAll
    public static void afterAll() {
        // Останавливаем приложение
        app.stop();
    }
    // END
}
