package service;

import model.BaseEntity;

import java.util.List;

public interface DbService<T extends BaseEntity> {
    void save(T dataSet);
    void update(T dataSet);
    T read(long id);
    List<T> readAll();
}
