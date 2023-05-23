package uni.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import uni.model.OurUser;


public class UserDAO {
    public boolean createAccount(OurUser user){
        Session session = FactoryManager.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(user);
        tx.commit();
        session.close();
        return true;
    }
    
    public OurUser checkExistance(String userEmail){
        Session session = FactoryManager.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM OurUser usr where usr.email = '"+userEmail+"'");
        OurUser user = (OurUser)query.uniqueResult();
        return user;
    }
    
    public OurUser authenticateUser(String userEmail, String password){
        Session session = FactoryManager.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM OurUser usr where usr.email = :userEmail AND usr.password= :password");
        query.setParameter("userEmail", userEmail);
        query.setParameter("password", password);
        OurUser user = (OurUser)query.uniqueResult();
        session.getTransaction().commit();
        return user;
    }
    
}
