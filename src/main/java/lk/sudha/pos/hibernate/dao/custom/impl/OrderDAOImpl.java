package lk.sudha.pos.hibernate.dao.custom.impl;


import lk.sudha.pos.hibernate.dao.CrudDAOImpl;
import lk.sudha.pos.hibernate.dao.custom.OrderDAO;
import lk.sudha.pos.hibernate.entity.Order;
import org.hibernate.query.NativeQuery;

public class OrderDAOImpl extends CrudDAOImpl<Order,Integer> implements OrderDAO {

    @Override
    public int getLastOrderId() throws Exception {
     return (int) session.createNativeQuery("SELECT id FROM `Order` ORDER BY id DESC LIMIT 1").uniqueResult();

        }

    @Override
    public boolean existsByCustomerId(String customerId) throws Exception {
        NativeQuery nativeQuery=session.createNativeQuery("SELECT * FROM `Order` WHERE customerId=?");
        nativeQuery.setParameter(1,customerId);

        return nativeQuery.uniqueResult()!=null;
    }


}
