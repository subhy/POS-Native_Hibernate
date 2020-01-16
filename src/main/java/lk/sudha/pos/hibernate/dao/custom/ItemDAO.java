package lk.sudha.pos.hibernate.dao.custom;


import lk.sudha.pos.hibernate.dao.CrudDAO;
import lk.sudha.pos.hibernate.entity.Item;

public interface ItemDAO extends CrudDAO<Item, String> {

    String getLastItemCode() throws Exception;
}
