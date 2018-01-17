package dao;


import model.BaseEntity;

import java.util.List;


public interface Dao<T extends BaseEntity> {
    List<T> readAll();

    T read(long id);

    void save(T dataSet);

    void update(T dataSet);
}
