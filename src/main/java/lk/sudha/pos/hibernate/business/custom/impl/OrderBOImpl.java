package lk.sudha.pos.hibernate.business.custom.impl;



import lk.sudha.pos.hibernate.business.custom.OrderBO;
import lk.sudha.pos.hibernate.dao.DAOFactory;
import lk.sudha.pos.hibernate.dao.DAOTypes;
import lk.sudha.pos.hibernate.dao.custom.ItemDAO;
import lk.sudha.pos.hibernate.dao.custom.OrderDAO;
import lk.sudha.pos.hibernate.dao.custom.OrderDetailDAO;
import lk.sudha.pos.hibernate.dao.custom.QueryDAO;
import lk.sudha.pos.hibernate.dto.OrderDTO;
import lk.sudha.pos.hibernate.dto.OrderDTO2;
import lk.sudha.pos.hibernate.dto.OrderDetailDTO;
import lk.sudha.pos.hibernate.entity.CustomEntity;
import lk.sudha.pos.hibernate.entity.Item;
import lk.sudha.pos.hibernate.entity.Order;
import lk.sudha.pos.hibernate.entity.OrderDetail;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    private OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);
    private OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAIL);
    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    private QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);

    @Override
    public int getLastOrderId() throws Exception {
        return orderDAO.getLastOrderId();
    }

    @Override
    public boolean placeOrder(OrderDTO order) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        try {

            // Let's start a transaction
            connection.setAutoCommit(false);

            int oId = order.getId();
            boolean result = orderDAO.save(new Order(oId, new java.sql.Date(new Date().getTime()),
                    order.getCustomerId()));

            if (!result) {
                connection.rollback();
                throw new RuntimeException("Something, something went wrong");
            }

            for (OrderDetailDTO orderDetail : order.getOrderDetails()) {
                result = orderDetailDAO.save(new OrderDetail(oId, orderDetail.getCode(),
                        orderDetail.getQty(), orderDetail.getUnitPrice()));

                if (!result) {
                    connection.rollback();
                    throw new RuntimeException("Something, something went wrong");
                }

                Item item = itemDAO.find(orderDetail.getCode());
                item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
                result = itemDAO.update(item);

                if (!result) {
                    connection.rollback();
                    throw new RuntimeException("Something, something went wrong");
                }
            }

            connection.commit();
            return true;

        } catch (Throwable e) {

            try {
                connection.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<OrderDTO2> getOrderInfo(String query) throws Exception {
        List<CustomEntity> ordersInfo = queryDAO.getOrdersInfo(query + "%");
        List<OrderDTO2> dtos = new ArrayList<>();
        for (CustomEntity info : ordersInfo) {
            dtos.add(new OrderDTO2(info.getOrderId(),
                    info.getOrderDate(),info.getCustomerId(),info.getCustomerName(),info.getOrderTotal()));
        }
        return dtos;
    }
}
