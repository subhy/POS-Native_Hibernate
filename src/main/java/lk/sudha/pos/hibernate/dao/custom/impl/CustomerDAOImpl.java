package lk.sudha.pos.hibernate.dao.custom.impl;



import lk.sudha.pos.hibernate.dao.CrudDAOImpl;
import lk.sudha.pos.hibernate.dao.custom.CustomerDAO;
import lk.sudha.pos.hibernate.entity.Customer;

public class CustomerDAOImpl extends CrudDAOImpl<Customer,String> implements CustomerDAO{

    @Override
    public String getLastCustomerId() throws Exception {
        return (String) session.createNativeQuery("SELECT customerId FROM Customer ORDER BY customerId DESC LIMIT 1").uniqueResult();

    }


}
