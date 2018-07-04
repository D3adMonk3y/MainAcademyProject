package DAO;

import Entity.ClientsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ClientDAO{
    private Session session;
    private Transaction t = null;


    public ClientDAO(Session session) {
        this.session = session;
    }

    public ClientsEntity getClient(Integer id){
        ClientsEntity clientsEntity = null;
        try{
                t = session.beginTransaction();
                clientsEntity = session.get(ClientsEntity.class, id);
                t.commit();
        }catch (HibernateException e ){
            e.printStackTrace();
            t.rollback();
        }
        return clientsEntity;
        }

        public void addClient(ClientsEntity clientsEntity){
        try{
            t = session.beginTransaction();
            session.save(clientsEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        }

        public void updateClient(ClientsEntity clientsEntity){
        try {
            t = session.beginTransaction();
            session.update(clientsEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        }

        public void deleteClient(ClientsEntity clientsEntity){
        try{
            t = session.beginTransaction();
            session.delete(clientsEntity);
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        }

        public List<ClientsEntity> getAllClients(){
        List<ClientsEntity> clients = null;
        try{
            t = session.beginTransaction();
            Query query = session.createQuery("FROM ClientsEntity");
            clients = query.list();
            t.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            t.rollback();
        }
        return clients;
        }
}
