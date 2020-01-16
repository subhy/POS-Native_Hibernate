package lk.sudha.pos.hibernate.dao.custom;


import lk.sudha.pos.hibernate.dao.CrudDAO;
import lk.sudha.pos.hibernate.entity.OrderDetail;
import lk.sudha.pos.hibernate.entity.OrderDetailPK;

public interface OrderDetailDAO extends CrudDAO<OrderDetail, OrderDetailPK> {

    boolean existsByItemCode(String itemCode) throws Exception;

}
