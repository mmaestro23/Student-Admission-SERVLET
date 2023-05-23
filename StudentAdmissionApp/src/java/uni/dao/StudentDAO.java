package uni.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import uni.model.Student;


public class StudentDAO {
    public boolean createAccount(Student student){
        Session session = FactoryManager.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(student);
        tx.commit();
        session.close();
        return true;
    }
    
    public Student checkExistance(String userEmail){
        Session session = FactoryManager.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query query = session.createQuery("FROM Student st where st.email = '"+userEmail+"'");
        Student student = (Student)query.uniqueResult();
        return student;
    }
    
}
