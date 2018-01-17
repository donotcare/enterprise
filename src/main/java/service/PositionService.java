package service;

import dao.Dao;
import dao.PositionDao;
import model.Position;
import org.hibernate.Session;

public class PositionService extends AbstractDbService<Position> {
    @Override
    protected Dao<Position> getDao(Session session) {
        return new PositionDao(session);
    }
}
