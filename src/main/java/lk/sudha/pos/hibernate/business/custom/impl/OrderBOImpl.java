package lk.sudha.pos.hibernate.business.custom.impl;



import lk.sudha.pos.hibernate.business.custom.OrderBO;
import lk.sudha.pos.hibernate.dao.DAOFactory;
import lk.sudha.pos.hibernate.dao.DAOTypes;
import lk.sudha.pos.hibernate.dao.custom.*;
import lk.sudha.pos.hibernate.db.HibernateUtil;
import lk.sudha.pos.hibernate.dto.OrderDTO;
import lk.sudha.pos.hibernate.dto.OrderDTO2;
import lk.sudha.pos.hibernate.dto.OrderDetailDTO;
import lk.sudha.pos.hibernate.entity.*;
import org.hibernate.Session;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    private OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);
    private OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAIL);
    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);
    private QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOTypes.QUERY);
    private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

    @Override
    public int getLastOrderId() throws Exception {
      try (Session session= HibernateUtil.getSessionFactory().openSession()){
          orderDAO.setSession(session);
          session.beginTransaction();
          int lastOrderId = orderDAO.getLastOrderId();
          session.getTransaction().commit();
          return lastOrderId;
      }
            }

    @Override
    public void placeOrder(OrderDTO order) throws Exception {
       try (Session session=HibernateUtil.getSessionFactory().openSession()){
           itemDAO.setSession(session);
           customerDAO.setSession(session);
           orderDAO.setSession(session);
           orderDetailDAO.setSession(session);
           session.beginTransaction();


            int oId = order.getId();
            orderDAO.save(new Order(oId, new java.sql.Date(new Date().getTime()),
                    session.load(Customer.class,order.getCustomerId())));

            for (OrderDetailDTO orderDetail : order.getOrderDetails()) {
                orderDetailDAO.save(new OrderDetail(oId, orderDetail.getCode(),
                        orderDetail.getQty(), orderDetail.getUnitPrice()));


                Item item = itemDAO.find(orderDetail.getCode());
                item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
                itemDAO.update(item);

            }

            session.getTransaction().commit();

        }
    }

    @Override
    public List<OrderDTO2> getOrderInfo(String query) throws Exception {
        try (Session session=HibernateUtil.getSessionFactory().openSession()){
            queryDAO.setSession(session);
            session.beginTransaction();
            List<CustomEntity> ordersInfo = queryDAO.getOrdersInfo(query + "%");
            List<OrderDTO2> dtos = new ArrayList<>();
            for (CustomEntity info : ordersInfo) {
                dtos.add(new OrderDTO2(info.getOrderId(),
                        info.getOrderDate(),info.getCustomerId(),info.getCustomerName(),info.getOrderTotal()));
            }
            return dtos;
        }
    }
}
