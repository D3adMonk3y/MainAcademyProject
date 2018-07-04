package DAO;

import Entity.ClientsEntity;
import Entity.LoginEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LoginDAO {
    private Session session;
    private Transaction t = null;


    public LoginDAO(Session session) {
        this.session = session;
    }

    public LoginEntity getLogin(Integer id){
        LoginEntity loginEntity = null;
        try{
            t = session.beginTransaction();
            loginEntity = session.get(LoginEntity.class, id);
            t.commit();
        }catch (HibernateException e ){
            e.printStackTrace();
            t.rollback();
        }
        return loginEntity;
    }

    public void addLogin(LoginEntity loginEntity){
        try{
            t = session.beginTransaction();
            session.save(loginEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void updateLogin(LoginEntity loginEntity){
        try {
            t = session.beginTransaction();
            session.update(loginEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void deleteLogin(LoginEntity loginEntity){
        try{
            t = session.beginTransaction();
            session.delete(loginEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public List<LoginEntity> getAllLogins(){
        List<LoginEntity> logins = null;
        try{
            t = session.beginTransaction();
            Query query = session.createQuery("FROM LoginEntity");
            logins = query.list();
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        return logins;
    }
}
