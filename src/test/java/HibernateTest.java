import model.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import service.DepartmentService;
import service.EmployeeService;
import service.PaymentService;
import service.PositionService;
import system.DBConfigHibernate;
import system.DbDataFiller;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

public class HibernateTest {
    private PositionService positionService = new PositionService();
    private PaymentService paymentService = new PaymentService();
    private DepartmentService departmentService = new DepartmentService();
    private EmployeeService employeeService = new EmployeeService();

    @Before
    public void before() throws IOException {
        DbDataFiller dataFiller = new DbDataFiller();
        dataFiller.parseCsvFile(new FileReader(this.getClass().getResource("/data.csv").getFile()));
    }

    @Test
    public void enterpriseTest() {
        List<Employee> employees = employeeService.readAll();
        System.out.println(employees);
        Employee firstEmployee = employees.get(0);
        String newName = "Mikhailo";
        firstEmployee.setName(newName);
        employeeService.update(firstEmployee);

        Employee updatedEmployee = employeeService.read(firstEmployee.getId());
        Assert.assertEquals(newName, updatedEmployee.getName());
        Assert.assertEquals(updatedEmployee, firstEmployee);

        int amountBeforeDelete = employees.size();
        Iterator<Employee> iterator = employees.iterator();
        IntStream.range(0, 3).forEach(i -> employeeService.delete(iterator.next()));

        List<Employee> employeesAfterDelete = employeeService.readAll();

        Assert.assertEquals(amountBeforeDelete  - 3, employeesAfterDelete.size());

        List res = DBConfigHibernate.getSession().createNativeQuery("CALL MAX_PAYMENT()").list();
        Assert.assertEquals("Regina", res.iterator().next());
        DBConfigHibernate.shutdown();
    }

}
