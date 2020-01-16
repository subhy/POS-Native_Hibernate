package lk.sudha.pos.hibernate.dao.custom.impl;


import lk.sudha.pos.hibernate.dao.CrudDAOImpl;
import lk.sudha.pos.hibernate.dao.custom.OrderDAO;
import lk.sudha.pos.hibernate.entity.Order;
import org.hibernate.query.NativeQuery;

public class OrderDAOImpl extends CrudDAOImpl<Order,Integer> implements OrderDAO {

    @Override
    public int getLastOrderId() throws Exception {
        Integer i = (Integer) session.createNativeQuery("SELECT id FROM `Order` ORDER BY id DESC LIMIT 1").uniqueResult();
         return (i==null)? 0 : i;

    }

    @Override
    public boolean existsByCustomerId(String customerId) throws Exception {
        return session.createNativeQuery("SELECT * FROM `Order` WHERE customerId=?").setParameter(1,customerId).uniqueResult()!=null;
    }


}
