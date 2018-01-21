package servlets;

import system.json.JsonHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class GetEmployeesJsonServlet extends HttpServlet {
    private final File xmlFile = new File(this.getClass().getResource("/employees.xml").getFile());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.getWriter().println(getEmployeesJson());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        setOK(resp);
    }

    public String getEmployeesJson() throws URISyntaxException {
        JsonHandler jsonHandler = new JsonHandler();
        String json = jsonHandler.xmlFileToJsonString(xmlFile);
        jsonHandler.saveJsonToFile(json, this.getClass().getResource("/employees.json").toURI());
        return json;
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
