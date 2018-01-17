import model.Department;
import model.Employee;
import model.Position;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.DepartmentService;
import service.EmployeeService;
import service.PaymentService;
import service.PositionService;
import system.DBConfigHibernate;

import java.util.List;

public class HibernateTest {
    private PositionService positionService = new PositionService();
    private PaymentService paymentService = new PaymentService();
    private DepartmentService departmentService = new DepartmentService();
    private EmployeeService employeeService = new EmployeeService();

    @Before
    public void before() {
        Department accountingDepartment = new Department("Accounting");
        departmentService.save(accountingDepartment);
        Position accountantPosition = new Position("Accountant");
        positionService.save(accountantPosition);
        Employee firstEmployee = new Employee("Alexey", accountingDepartment, "Moscow", accountantPosition, "test", "test");
        employeeService.save(firstEmployee);
    }

    @Test
    public void enterpriseTest(){
        List<Employee> employees = employeeService.readAll();
        Employee firstEmployee = employees.get(0);
        String newName = "Mikhail";
        firstEmployee.setName(newName);
        employeeService.update(firstEmployee);

        Employee updatedEmployee = employeeService.read(firstEmployee.getId());
        Assert.assertEquals(newName, updatedEmployee.getName());
        Assert.assertEquals(updatedEmployee, firstEmployee);
        System.out.println(updatedEmployee);
        System.out.println(firstEmployee);
        DBConfigHibernate.shutdown();
    }

}
