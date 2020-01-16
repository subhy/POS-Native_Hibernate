package lk.sudha.pos.hibernate.dao.custom.impl;



import lk.sudha.pos.hibernate.dao.CrudDAOImpl;
import lk.sudha.pos.hibernate.dao.custom.ItemDAO;
import lk.sudha.pos.hibernate.entity.Item;

public class ItemDAOImpl extends CrudDAOImpl<Item,String> implements ItemDAO {

    @Override
    public String getLastItemCode() throws Exception {
       return (String) session.createNativeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1").uniqueResult();

    }


}
