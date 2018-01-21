package servlets;

import model.Employee;
import org.w3c.dom.NodeList;
import service.EmployeeService;
import system.xml.EmployeeMarshaller;
import system.xml.Employees;
import system.xml.EntityMarshaller;
import system.xml.XPathHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MarshalXMLServlet extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeService();
    private final File xmlFile = new File(this.getClass().getResource("/employees.xml").getFile());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            resp.getWriter().println(getEmployeesWithPaymentMoreThanAvg());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        setOK(resp);
    }

    private String getEmployeesWithPaymentMoreThanAvg() throws XPathExpressionException {
        List<Employee> employeesList = employeeService.readAll();
        marshal(employeesList);

        XPathHandler xpathHandler = new XPathHandler(xmlFile);
        double averagePaymentAmount = employeesList.stream().mapToDouble(e -> e.getPayment().getAmount().doubleValue()).average().getAsDouble();
        NodeList nodes = xpathHandler.getNodesByXpath("employees/employee[payment/amount > " + averagePaymentAmount + "]/name/text()");
        return IntStream.range(0, nodes.getLength()).mapToObj(i -> nodes.item(i).getNodeValue()).collect(Collectors.joining(", "));
    }

    private void marshal(List<Employee> employeesList) {
        EntityMarshaller marshaller = new EmployeeMarshaller();
        Employees employees = new Employees(employeesList);
        marshaller.marshal(employees, xmlFile);
    }

    private void setOK(HttpServletResponse response) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
