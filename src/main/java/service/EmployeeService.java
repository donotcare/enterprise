package service;

import dao.Dao;
import dao.EmployeeDao;
import model.Employee;
import org.hibernate.Session;

public class EmployeeService extends AbstractDbService<Employee> {
    @Override
    protected Dao<Employee> getDao(Session session) {
        return new EmployeeDao(session);
    }
}
