package dao;


import model.Department;
import org.hibernate.Session;

public class DepartmentDao extends AbstractDao<Department> implements Dao<Department> {

    public DepartmentDao(Session session) {
        super(session);
    }

}
