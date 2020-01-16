package lk.sudha.pos.hibernate.dao.custom.impl;



import lk.sudha.pos.hibernate.dao.CrudDAOImpl;
import lk.sudha.pos.hibernate.dao.custom.OrderDetailDAO;
import lk.sudha.pos.hibernate.entity.OrderDetail;
import lk.sudha.pos.hibernate.entity.OrderDetailPK;

public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail,OrderDetailPK> implements OrderDetailDAO {

     @Override
    public boolean existsByItemCode(String itemCode) throws Exception {
        return (boolean) session.createNativeQuery("SELECT * FROM OrderDetail WHERE itemCode=?1")
                .setParameter(1,itemCode).uniqueResult();

    }
}
