package DAO;

import Entity.OrdersEntity;
import Entity.StatusEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class StatusDAO {
    private Session session;
    private Transaction t = null;


    public StatusDAO(Session session) {
        this.session = session;
    }

    public StatusEntity getStatus(Integer id){
        StatusEntity statusEntity = null;
        try{
            t = session.beginTransaction();
            statusEntity = session.get(StatusEntity.class, id);
            t.commit();
        }catch (HibernateException e ){
            e.printStackTrace();
            t.rollback();
        }
        return statusEntity;
    }

    public void addStatus(StatusEntity statusEntity){
        try{
            t = session.beginTransaction();
            session.save(statusEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void updateStatus(StatusEntity statusEntity){
        try {
            t = session.beginTransaction();
            session.update(statusEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void deleteStatus(StatusEntity statusEntity){
        try{
            t = session.beginTransaction();
            session.delete(statusEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public List<StatusEntity> getAllStatus(){
        List<StatusEntity> status = null;
        try{
            t = session.beginTransaction();
            Query query = session.createQuery("FROM StatusEntity");
            status = query.list();
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        return status;
    }
}
