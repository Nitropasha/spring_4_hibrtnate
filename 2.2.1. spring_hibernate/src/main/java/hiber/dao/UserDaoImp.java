package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }



   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   @SuppressWarnings("unchecked")
   public User userByCar(String modelName, Integer className) {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User where userCar.model = :modelName and userCar.series = :className");
      query.setParameter("modelName", modelName);
      query.setParameter("className", className);
      query.setMaxResults(1);
      return ((Query<User>) query).uniqueResult();

   }

}
