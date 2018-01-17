package dao;


import model.Payment;
import org.hibernate.Session;

public class PaymentDao extends AbstractDao<Payment> implements Dao<Payment> {

    public PaymentDao(Session session) {
        super(session);
    }

}
