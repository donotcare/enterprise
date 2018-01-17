package dao;


import model.BaseEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDao<T extends BaseEntity> implements Dao<T> {
    private final Class<T> persistentClass;
    private Session session;

    public AbstractDao(Session session) {
        this.session = session;
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void save(T dataSet) {
        session.save(dataSet);
    }

    public void update(T dataSet) {
        session.merge(dataSet);
    }

    public T read(long id) {
        return session.load(persistentClass, id);
    }

    public List<T> readAll() {
        Criteria criteria = session.createCriteria(persistentClass);
        return criteria.list();
    }
}
