package lk.sudha.pos.hibernate.business.custom;



import lk.sudha.pos.hibernate.business.SuperBO;
import lk.sudha.pos.hibernate.dto.OrderDTO;
import lk.sudha.pos.hibernate.dto.OrderDTO2;

import java.util.List;

public interface OrderBO extends SuperBO {

    int getLastOrderId() throws Exception;

    boolean placeOrder(OrderDTO orderDTO) throws Exception;

    List<OrderDTO2> getOrderInfo(String query) throws Exception;

}
