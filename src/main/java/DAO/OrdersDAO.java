package DAO;

import Entity.ClientsEntity;
import Entity.OrdersEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OrdersDAO {
    private Session session;
    private Transaction t = null;


    public OrdersDAO(Session session) {
        this.session = session;
    }

    public OrdersEntity getOrders(Integer id){
        OrdersEntity ordersEntity = null;
        try{
            t = session.beginTransaction();
            ordersEntity = session.get(OrdersEntity.class, id);
            t.commit();
        }catch (HibernateException e ){
            e.printStackTrace();
            t.rollback();
        }
        return ordersEntity;
    }

    public void addOrder(OrdersEntity ordersEntity){
        try{
            t = session.beginTransaction();
            session.save(ordersEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void updateOrders(OrdersEntity ordersEntity){
        try {
            t = session.beginTransaction();
            session.update(ordersEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void deleteOrders(OrdersEntity ordersEntity){
        try{
            t = session.beginTransaction();
            session.delete(ordersEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public List<OrdersEntity> getAllOrders(){
        List<OrdersEntity> orders = null;
        try{
            t = session.beginTransaction();
            Query query = session.createQuery("FROM OrdersEntity");
            orders = query.list();
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        return orders;
    }
}
