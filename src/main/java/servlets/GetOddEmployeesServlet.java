package servlets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Employee;
import system.xml.Employees;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GetOddEmployeesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            resp.getWriter().println(getOddEmployees());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setOK(resp);
    }

    private String getOddEmployees() throws IOException, URISyntaxException {
        Path path = Paths.get(this.getClass().getResource("/employees.json").toURI());
        String json = new String(Files.readAllBytes(path));
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map<String, Employees>>() {}.getType();
        Map<String, Employees> map = gson.fromJson(json, mapType);
        List<Employee> employeesFromJson = map.get("employees").getEmployeeList();
        return IntStream.range(0, employeesFromJson.size()).filter(i -> i % 2 == 0)
                .mapToObj(i -> employeesFromJson.get(i).getName())
                .collect(Collectors.joining(", "));
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
