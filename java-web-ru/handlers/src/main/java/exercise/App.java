package exercise;

import io.javalin.Javalin;

import java.util.List;

public final class App {

    public static Javalin getApp() {
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        List<String> phones = Data.getPhones();
        app.get("/phones", ctx -> ctx.json(phones));

        List<String> domains = Data.getDomains();
        app.get("/domains", ctx -> ctx.json(domains));

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
