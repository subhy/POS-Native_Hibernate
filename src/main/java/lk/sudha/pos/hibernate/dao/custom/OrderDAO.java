package lk.sudha.pos.hibernate.dao.custom;


import lk.sudha.pos.hibernate.dao.CrudDAO;
import lk.sudha.pos.hibernate.entity.Order;

public interface OrderDAO extends CrudDAO<Order, Integer> {

    int getLastOrderId() throws Exception;

    boolean existsByCustomerId(String customerId) throws Exception;

}
