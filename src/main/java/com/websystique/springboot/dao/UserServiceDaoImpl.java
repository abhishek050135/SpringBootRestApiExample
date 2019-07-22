package com.websystique.springboot.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.websystique.springboot.configuration.HibernateUtil;
import com.websystique.springboot.entity.UserInformation;
import com.websystique.springboot.model.User;

@Repository
public class UserServiceDaoImpl implements UserServiceDao {

	SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Override
	public UserInformation findById(int id) {
		
		UserInformation userInformation = null;
		System.out.println("Find by id from DAO called.");

		Session session = sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		Query query = session.createQuery("FROM UserInformation WHERE userId = :id");
		query.setParameter("id", id);

		userInformation = (UserInformation) query.getSingleResult();
		trans.commit();

		return userInformation;
	}

	@Override
	public UserInformation findByName(String name) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		Query query = session.createQuery("FROM UserInformation WHERE Name = :name");
		query.setParameter("name", name);

		UserInformation userInformation = (UserInformation) query.getSingleResult();
		trans.commit();
		
		return userInformation;
	}

	@Override
	public void saveUser(UserInformation user) {
		
		Session session = sessionFactory.openSession();
		Transaction trans=session.beginTransaction();
		session.save(user);
		trans.commit();
		
	}

	@Override
	public void updateUser(UserInformation user) {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		
		UserInformation userInformationEntity = new UserInformation();
		userInformationEntity.setUserId(user.getUserId());
		userInformationEntity.setName(user.getName());
		userInformationEntity.setAge(user.getAge());
		userInformationEntity.setSalary(user.getSalary());
		
		session.saveOrUpdate(userInformationEntity);
		trans.commit();

	}

	@Override
	public void deleteUserById(int id) {
		
		Session session  = sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		Query query = session.createQuery("DELETE FROM UserInformation where Id = :id");
		query.setParameter("id", id);
		query.executeUpdate();
		trans.commit();
		
	}

	@Override
	@SuppressWarnings({ "unchecked" })
	public List<UserInformation> findAllUsers() {
		
		Session session = sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		List<UserInformation> list = session.createQuery("FROM UserInformation").list();
		trans.commit();
		
		return list;
	}

	@Override
	public void deleteAllUsers() {

		Session session  = sessionFactory.getCurrentSession();
		Transaction trans=session.beginTransaction();
		session.createQuery("DELETE FROM UserInformation").executeUpdate();
		trans.commit();
		
	}

	@Override
	public boolean isUserExist(User user) {
		
		Session session  = sessionFactory.getCurrentSession();
		
		Transaction trans = session.beginTransaction();
		UserInformation userInformationEntity = new UserInformation();
		userInformationEntity.setUserId(user.getId());
		userInformationEntity.setName(user.getName());
		userInformationEntity.setAge(user.getAge());
		userInformationEntity.setSalary(user.getSalary());
		
		session.byId(UserInformation.class);
		trans.commit();
		session.close();
		
		return false;
	}
}
