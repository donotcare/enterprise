package dao;


import model.Employee;
import org.hibernate.Session;

public class EmployeeDao extends AbstractDao<Employee> implements Dao<Employee> {

    public EmployeeDao(Session session) {
        super(session);
    }

}
