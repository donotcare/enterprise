package service;

import dao.Dao;
import model.BaseEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import system.DBConfigHibernate;

import java.util.List;
import java.util.function.Function;

public abstract class AbstractDbService<T extends BaseEntity> implements DbService<T> {

    public void save(T dataSet) {
        try (Session session = DBConfigHibernate.getSession()) {
            Dao<T> dao = getDao(session);
            dao.save(dataSet);
        }
    }

    public void update(T dataSet) {
        try (Session session = DBConfigHibernate.getSession()) {
            Dao<T> dao = getDao(session);
            dao.update(dataSet);
        }
    }

    @Override
    public void delete(T dataSet) {
        try (Session session = DBConfigHibernate.getSession()) {
            Dao<T> dao = getDao(session);
            dao.delete(dataSet);
        }
    }

    public T read(long id) {
        return runInSession(session -> {
            Dao<T> dao = getDao(session);
            return dao.read(id);
        });
    }

    public List<T> readAll() {
        return runInSession(session -> {
            Dao<T> dao = getDao(session);
            return dao.readAll();
        });
    }

    protected <R> R runInSession(Function<Session, R> function) {
        try (Session session = DBConfigHibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    protected abstract Dao<T> getDao(Session session);
}
