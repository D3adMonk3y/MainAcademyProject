package DAO;

import Entity.ClientsEntity;
import Entity.EmployesEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class EmployeeDAO {
    private Session session;
    private Transaction t = null;

    public EmployeeDAO (Session session){
        this.session = session;
    }

    public void addEmployee(EmployesEntity employesEntity){
        try{
            t = session.beginTransaction();
            session.save(employesEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }
    public EmployesEntity getEmployee(Integer id){
        EmployesEntity employesEntity = null;
        try{
            t = session.beginTransaction();
            employesEntity = session.get(EmployesEntity.class, id);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        return employesEntity;
    }

    public void updateEmployee(EmployesEntity employesEntity){
        try {
            t = session.beginTransaction();
            session.update(employesEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void deleteEmployee(EmployesEntity employesEntity){
        try{
            t = session.beginTransaction();
            session.delete(employesEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public List<EmployesEntity> getAllEmployes(){
        List<EmployesEntity> employees = null;
        try{
            t = session.beginTransaction();
            Query query = session.createQuery("FROM EmployesEntity");
            employees = query.list();
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        return employees;
    }
}
