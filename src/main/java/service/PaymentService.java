package service;

import dao.Dao;
import dao.PaymentDao;
import model.Payment;
import org.hibernate.Session;

public class PaymentService extends AbstractDbService<Payment> {
    @Override
    protected Dao<Payment> getDao(Session session) {
        return new PaymentDao(session);
    }
}
