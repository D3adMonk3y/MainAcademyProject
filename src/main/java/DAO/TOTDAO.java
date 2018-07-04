package DAO;

import Entity.OrdersEntity;
import Entity.TypeoftecnicsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TOTDAO {
    private Session session;
    private Transaction t = null;


    public TOTDAO (Session session) {
        this.session = session;
    }

    public TypeoftecnicsEntity getTOT(Integer id){
        TypeoftecnicsEntity tot = null;
        try{
            t = session.beginTransaction();
            tot = session.get(TypeoftecnicsEntity.class, id);
            t.commit();
        }catch (HibernateException e ){
            e.printStackTrace();
            t.rollback();
        }
        return tot;
    }

    public TypeoftecnicsEntity getTOT(String type){
        TypeoftecnicsEntity tot = null;
        try{
            t = session.beginTransaction();
            tot = session.get(TypeoftecnicsEntity.class, type);
            t.commit();
        }catch (HibernateException e ){
            e.printStackTrace();
            t.rollback();
        }
        return tot;
    }

    public void addTOT(TypeoftecnicsEntity tot){
        try{
            t = session.beginTransaction();
            session.save(tot);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void updateTOT(TypeoftecnicsEntity tot){
        try {
            t = session.beginTransaction();
            session.update(tot);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void deleteTOT(TypeoftecnicsEntity tot){
        try{
            t = session.beginTransaction();
            session.delete(tot);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public List<TypeoftecnicsEntity> getAllTOTs(){
        List<TypeoftecnicsEntity> tots = null;
        try{
            t = session.beginTransaction();
            Query query = session.createQuery("FROM TypeoftecnicsEntity");
            tots = query.list();
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        return tots;
    }
}
