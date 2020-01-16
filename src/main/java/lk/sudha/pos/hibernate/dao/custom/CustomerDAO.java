package lk.sudha.pos.hibernate.dao.custom;


import lk.sudha.pos.hibernate.dao.CrudDAO;
import lk.sudha.pos.hibernate.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer, String> {

    String getLastCustomerId() throws Exception;

}
