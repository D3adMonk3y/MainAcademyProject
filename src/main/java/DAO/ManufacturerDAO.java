package DAO;

import Entity.ClientsEntity;
import Entity.ManufacturerEntity;
import Entity.ModelEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ManufacturerDAO {
    private Session session;
    private Transaction t = null;


    public ManufacturerDAO(Session session) {
        this.session = session;
    }

    public ManufacturerEntity getManufacturer(Integer id){
        ManufacturerEntity manufacturerEntity = null;
        try{
            t = session.beginTransaction();
            manufacturerEntity = session.get(ManufacturerEntity.class, id);
            t.commit();
        }catch (HibernateException e ){
            e.printStackTrace();
            t.rollback();
        }
        return manufacturerEntity;
    }

    public void addManufacturer(ManufacturerEntity manufacturerEntity){
        try{
            t = session.beginTransaction();
            session.save(manufacturerEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void updateManufacturer(ManufacturerEntity manufacturerEntity){
        try {
            t = session.beginTransaction();
            session.update(manufacturerEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public void deleteManufacturer(ManufacturerEntity manufacturerEntity){
        try{
            t = session.beginTransaction();
            session.delete(manufacturerEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
    }

    public List<ManufacturerEntity> getAllManufacturers(){
        List<ManufacturerEntity> manufacturers = null;
        try{
            t = session.beginTransaction();
            Query query = session.createQuery("FROM ManufacturerEntity");
            manufacturers = query.list();
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        return manufacturers;
    }
}
