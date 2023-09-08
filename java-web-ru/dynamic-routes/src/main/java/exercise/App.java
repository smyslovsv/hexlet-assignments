package exercise;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            var companyNumber = Integer.parseInt(ctx.pathParam("id"));

            if (companyNumber > COMPANIES.size() || companyNumber < 0) {
                throw new NotFoundResponse("Company not found");
            }
            Map<String, String> company = null;
            for (Map<String, String> item : COMPANIES) {
                if (Integer.parseInt(item.get("id")) == companyNumber) {
                    company = new HashMap<>(item);
                    break;
                }
                System.out.println(item);
            }
            ctx.json(company);
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
