package service;

import dao.Dao;
import dao.DepartmentDao;
import model.Department;
import org.hibernate.Session;

public class DepartmentService extends AbstractDbService<Department> {
    @Override
    protected Dao<Department> getDao(Session session) {
        return new DepartmentDao(session);
    }
}
