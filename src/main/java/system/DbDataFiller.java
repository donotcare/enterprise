package system;

import model.Department;
import model.Employee;
import model.Payment;
import model.Position;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import service.DepartmentService;
import service.EmployeeService;
import service.PaymentService;
import service.PositionService;

import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DbDataFiller {
    private PositionService positionService = new PositionService();
    private PaymentService paymentService = new PaymentService();
    private DepartmentService departmentService = new DepartmentService();
    private EmployeeService employeeService = new EmployeeService();

    private Map<String, Position> positions = new HashMap<>();
    private Map<String, Department> departments = new HashMap<>();
    private Map<Position, Payment>  payments = new HashMap<>();

    public void parseCsvFile(Reader in) throws IOException {
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            String name = record.get("Name");
            String city = record.get("City");
            Department department = getDepartment(record.get("Department"));
            Position position = getPosition(record.get("Position"));
            Payment payment = getPayment(position, record.get("Payment"));
            String login = record.get("Login");
            String password = record.get("Password");
            createEmployee(name, city, department, position, payment, login, password);
        }
    }

    private void createEmployee(String name, String city, Department department, Position position, Payment payment, String login, String password) {
        employeeService.save(new Employee(name, department, city, position, payment, login, password));
    }

    private Department getDepartment(String department) {
        return departments.computeIfAbsent(department, this::createDepartment);
    }

    private Department createDepartment(String departmentName) {
        Department department = new Department(departmentName);
        departmentService.save(department);
        departments.put(departmentName, department);
        return department;
    }

    private Position getPosition(String positionName) {
        return positions.computeIfAbsent(positionName, this::createPosition);
    }

    private Position createPosition(String positionName) {
        Position position = new Position(positionName);
        positionService.save(position);
        positions.put(positionName, position);
        return position;
    }

    private Payment getPayment(Position position, String paymentAmount) {
        return payments.computeIfAbsent(position, p -> createPayment(p, paymentAmount));
    }

    private Payment createPayment(Position position, String paymentAmount){
        Payment payment = new Payment(position, new BigDecimal(paymentAmount));
        paymentService.save(payment);
        return payment;
    }
}
