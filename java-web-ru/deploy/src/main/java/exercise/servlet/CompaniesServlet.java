package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        List<String> searchResult = getCompanies();

        // BEGIN
        PrintWriter out = response.getWriter();
        String queryString = request.getQueryString();
        String paramString = request.getParameter("search");

        if (queryString == null) {
            searchResult.forEach(out::println);
        } else {
            if (!queryString.isEmpty()) {
                if (paramString == null) {
                    searchResult.forEach(out::println);
                } else {
                    if (paramString.isEmpty()) {
                        searchResult.forEach(out::println);
                    } else {
                        searchResult = searchResult.stream()
                                .filter(company -> company.contains(request.getParameter("search")))
                                .toList();
                        if (searchResult.isEmpty()) {
                            out.println("Companies not found");
                        } else {
                            searchResult.forEach(out::println);
                        }
                    }
                }
            } else {
                searchResult.forEach(out::println);
            }
        }
        // END
    }
}
