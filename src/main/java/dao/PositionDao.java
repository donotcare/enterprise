package dao;


import model.Position;
import org.hibernate.Session;

public class PositionDao extends AbstractDao<Position> implements Dao<Position> {

    public PositionDao(Session session) {
        super(session);
    }

}
