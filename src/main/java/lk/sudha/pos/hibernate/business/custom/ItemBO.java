package lk.sudha.pos.hibernate.business.custom;



import lk.sudha.pos.hibernate.business.SuperBO;
import lk.sudha.pos.hibernate.dto.ItemDTO;

import java.util.List;

public interface ItemBO extends SuperBO {

    void saveItem(ItemDTO itemDTO) throws Exception;

    void updateItem(ItemDTO item) throws Exception;

    void deleteItem(String itemCode) throws Exception;

    List<ItemDTO> findAllItems() throws Exception;

    String getLastItemCode() throws Exception;

    ItemDTO findItem(String itemCode) throws Exception;

    List<String> getAllItemCodes() throws Exception;

}
