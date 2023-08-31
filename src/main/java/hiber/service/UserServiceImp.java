package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Queue;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private SessionFactory sessionFactory;

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @PersistenceContext
   private EntityManager entityManager;

   @Override
   public List<User> findUserByAuto(String mod, String ser) {
      try{
         TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u JOIN u.car c WHERE c.model = :mod AND c.series = :ser", User.class);
         query.setParameter("mod", mod);
         query.setParameter("ser", ser);
         query.setMaxResults(1);

         return query.getResultList();
      } catch (javax.persistence.NoResultException e) {
         return null;
      }
   }

}
