package manager;

import com.codahale.metrics.annotation.ResponseMetered;
import entity.StudentEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

public class StudentManagerImpl implements StudentManager{

    private static final SessionFactory SESSION_FACTORY;
    static {
        Configuration config = new Configuration();
        config.configure();
        Properties prop = config.getProperties();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(prop);
        ServiceRegistry registry = builder.build();
        SESSION_FACTORY = config.buildSessionFactory(registry);
    }

    @Override
    //@ResponseMetered(name = "createFunction")
    public String create(StudentEntity studentEntity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = null;
        try {
            // Begin a transaction
            transaction = session.beginTransaction();
            StudentEntity stu = new StudentEntity();
            stu.setId(studentEntity.getId());
            stu.setName(studentEntity.getName());
            stu.setAge(studentEntity.getAge());
            // Save the student
            session.saveOrUpdate(stu);
            // Commit the transaction
            transaction.commit();
        } catch (HibernateException ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the session
            session.close();
        }
        return "Successfully registered";
    }

    @Override
    public StudentEntity get(int id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = null;
        StudentEntity stu =null;
        try {
            // Begin a transaction
            transaction = session.beginTransaction();
            // Get the Student from the database.
            stu = (StudentEntity) session.get(StudentEntity.class, Integer.valueOf(id));
            transaction.commit();
        } catch (HibernateException ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the session
            session.close();
        }
        return stu;
    }

    @Override
    public List<StudentEntity> getAll() {

        List<StudentEntity> students = null;
        // Create a session
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = null;
        try {
            // Begin a transaction
            transaction = session.beginTransaction();
            students = session.createQuery("FROM StudentEntity").list();
            // Commit the transaction
            transaction.commit();
        } catch (HibernateException ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            // Print the Exception
            ex.printStackTrace();
        } finally {
            // Close the session
            session.close();
        }
        return students;

    }
}
