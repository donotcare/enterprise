import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.NodeList;
import service.EmployeeService;
import system.*;
import system.json.JsonHandler;
import system.xml.EmployeeMarshaller;
import system.xml.Employees;
import system.xml.EntityMarshaller;
import system.xml.XPathHandler;

import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class XmlJsonTest {
    private EmployeeService employeeService = new EmployeeService();

    @Before
    public void before() throws IOException {
        DbDataFiller dataFiller = new DbDataFiller();
        dataFiller.parseCsvFile(new FileReader(this.getClass().getResource("/data.csv").getFile()));
    }

    @Test
    public void xmlJsonTest() throws XPathExpressionException, URISyntaxException {
        List<Employee> employeesList = employeeService.readAll();
        EntityMarshaller marshaller = new EmployeeMarshaller();
        File file = new File(this.getClass().getResource("/employees.xml").getFile());
        Employees employees = new Employees(employeesList);
        marshaller.marshal(employees, file);

        double averagePaymentAmount = employeesList.stream().mapToDouble(e -> e.getPayment().getAmount().doubleValue()).average().getAsDouble();

        XPathHandler xpathHandler = new XPathHandler(file);
        NodeList nodes = xpathHandler.getNodesByXpath("employees/employee[payment/amount > " + averagePaymentAmount + "]/name/text()");
        Assert.assertEquals(3, nodes.getLength());

        JsonHandler jsonHandler = new JsonHandler();
        String json = jsonHandler.xmlFileToJsonString(file);
        jsonHandler.saveJsonToFile(json, this.getClass().getResource("/employees.json").toURI());

        Gson gson =  new Gson();
        Type mapType = new TypeToken<Map<String, Employees>>() {}.getType();
        Map<String, Employees> map = gson.fromJson(json, mapType);
        List<Employee> employeesFromJson = map.get("employees").getEmployeeList();
        List<Employee> filteredEmployees = IntStream.range(0, employeesFromJson.size()).filter(i -> i % 2 == 0).mapToObj(i -> employeesFromJson.get(i)).collect(Collectors.toList());
        Assert.assertEquals(4, filteredEmployees.size());
    }
}
