package lk.sudha.pos.hibernate.dao.custom.impl;



import lk.sudha.pos.hibernate.dao.custom.QueryDAO;
import lk.sudha.pos.hibernate.entity.CustomEntity;
import org.hibernate.Session;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {

    private Session session;
    @Override
    public CustomEntity getOrderInfo(int orderId) throws Exception {
     /*   ResultSet rst = CrudUtil.execute("SELECT C.customerId, C.name, O.date  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customerId WHERE O.id=?", orderId);
        if (rst.next()){
            return new CustomEntity(orderId,
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3));
        }else{
            return null;
        }*/
     return null;
    }

    @Override
   public CustomEntity getOrderInfo2(int orderId) throws Exception {
/*        ResultSet rst = CrudUtil.execute("SELECT O.id, C.customerId, C.name, O.date, SUM(OD.qty * OD.unitPrice) AS Total  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customerId\" +\n" +
                "                \" INNER JOIN OrderDetail OD on O.id = OD.orderId WHERE O.id=? GROUP BY orderId", orderId);
        if (rst.next()){
            return new CustomEntity(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getDouble(5));
        }else{
            return null;
        }*/
    return null;
    }

    @Override
    public List<CustomEntity> getOrdersInfo(String query) throws Exception {
  /*      NativeQuery nativeQuery= session.createNativeQuery("SELECT O.id, C.customerId, C.name, O.date, SUM(OD.qty * OD.unitPrice) AS Total  FROM Customer C INNER JOIN `Order` O ON C.customerId=O.customerId " +
                "INNER JOIN OrderDetail OD on O.id = OD.orderId WHERE O.id LIKE ? OR C.customerId LIKE ? OR C.name LIKE ? OR O.date LIKE ? GROUP BY O.id");

        nativeQuery.setParameter(1,query);
        nativeQuery.setParameter(2,query);
        nativeQuery.setParameter(3,query);
        nativeQuery.setParameter(4,query);

        List<CustomEntity> al = new ArrayList<>();
        while (rst.next()){
            al.add(new CustomEntity(rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDate(4),
                    rst.getDouble(5)));
        }
        return al;
  */
    return null;
    }

    @Override
    public void setSession(Session session) {
        this.session=session;
            }
}
